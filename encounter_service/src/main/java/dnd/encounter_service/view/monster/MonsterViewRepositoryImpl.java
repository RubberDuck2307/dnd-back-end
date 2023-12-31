package dnd.encounter_service.view.monster;

import dnd.encounter_service.config.SqlConfig;
import dnd.encounter_service.view.monster.entity.MonsterView;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MonsterViewRepositoryImpl {

    private final EntityManager entityManager;

    @Autowired
    public MonsterViewRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @param crs Map where the keys represent the cr and the values the amount of monsters with that cr that should be returned
     * @return HashMap containing monsters with the corresponding crs as keys
     */
    public Map<Float, List<MonsterView>> getMonstersByCrAndAmount(Map<Float, Integer> crs) {
        return getMonstersByCrAmountAndMonsterGroupId(crs, 0);
    }

    public Map<Float, Integer> getAmountOfMonstersByCrAndMonsterGroup(Map<Float, Integer> crs,
                                                                      long monsterGroupId) {
        Session session = entityManager.unwrap(Session.class);

        if (crs.isEmpty())
            return new HashMap<>();

        StringBuilder countQueryString = new StringBuilder("SELECT COUNT(*)\n" +
                "FROM " +
                SqlConfig.SCHEMA + "." +
                SqlConfig.MONSTER_VIEW_TABLE
                + "\n" +
                "where cr = :cr1\n");

        addMonsterGroupCondition(monsterGroupId, countQueryString);


        for (int i = 1; i < crs.size(); i++) {
            countQueryString.append("\n UNION ALL SELECT COUNT(*)\n" +
                    "FROM " +
                    SqlConfig.SCHEMA + "." +
                    SqlConfig.MONSTER_VIEW_TABLE +
                    "\n" +
                    "where cr = :cr").append(i + 1).append("\n");
            addMonsterGroupCondition(monsterGroupId, countQueryString);
        }

        Query<Integer> countQuery = session.createNativeQuery(countQueryString.toString(), Integer.class);

        Float[] crsKeyArray = crs.keySet().toArray(new Float[0]);
        for (int i = 0; i < crs.size(); i++) {
            countQuery.setParameter("cr" + (i + 1), crsKeyArray[i]);
        }
        if (monsterGroupId != 0)
            countQuery.setParameter("monsterGroupId", monsterGroupId);

        return convertCountQueryResultToMap(crs, countQuery.getResultList());
    }

    public Map<Float, List<MonsterView>> getMonstersByCrAmountAndMonsterGroupId(Map<Float, Integer> crs,
                                                                            long monsterGroupId) {
        Session session = entityManager.unwrap(Session.class);

        Map<Float, Integer> amountOfMonstersByCr = getAmountOfMonstersByCrAndMonsterGroup(crs, monsterGroupId);

        Float[] crsKeyArray = crs.keySet().toArray(new Float[0]);

        StringBuilder queryString = new StringBuilder("(SELECT *\n" +
                "FROM " +
                SqlConfig.SCHEMA + "." +
                SqlConfig.MONSTER_VIEW_TABLE +
                "\n" +
                "where cr = :cr1\n");

        addMonsterGroupCondition(monsterGroupId, queryString);

        queryString.append("OFFSET floor(random() * :amt1)\n " +
                "LIMIT :lmt1)");

        for (int i = 1; i < crs.size(); i++) {
            queryString.append("\nUNION (SELECT *\n" +
                    "FROM " +
                    SqlConfig.SCHEMA + "." +
                    SqlConfig.MONSTER_VIEW_TABLE +
                    "\n" +
                    "where cr = :cr").append(i + 1).append("\n");

            addMonsterGroupCondition(monsterGroupId, queryString);

            queryString.append("OFFSET floor(random() * :amt").append(i + 1).append(")\n" +
                    "LIMIT :lmt").append(i + 1).append(")");
        }

        Query<MonsterView> query = session.createNativeQuery(queryString.toString(), MonsterView.class);

        for (int i = 0; i < crs.size(); i++) {
            int amt = Math.max(amountOfMonstersByCr.get(crsKeyArray[i]) - crs.get(crsKeyArray[i]), 0);
            query.setParameter("cr" + (i + 1), crsKeyArray[i]);
            query.setParameter("lmt" + (i + 1), crs.get(crsKeyArray[i]));
            //set maximum possible offset. If there are fewer monsters in database than requested, set offset to 0
            query.setParameter("amt" + (i + 1), amt);
        }
        if (monsterGroupId != 0) {
            query.setParameter("monsterGroupId", monsterGroupId);
        }
        List<MonsterView> monsterList = null;
        try {
             monsterList = query.getResultList();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
        Map<Float, List<MonsterView>> monsters = convertMonsterQueryResultToMap(crs, amountOfMonstersByCr,
                monsterList);
        return monsters;
    }

    private void addMonsterGroupCondition(long monsterGroupId, StringBuilder countQueryString) {
        if (monsterGroupId != 0) {
            countQueryString.append("AND id IN (SELECT monster_id\n" +
                            "FROM ")
                    .append(SqlConfig.SCHEMA).append(".")
                    .append(SqlConfig.MONSTER_VIEW_GROUP_MONSTER_TABLE)
                    .append("\nWHERE monster_group_id = :monsterGroupId)\n");
        }
    }

    /**
     * Converts the result of the query to a hashmap with cr as key and list of monsters as value
     *
     * @param crs                  amounts of requested monsters by cr
     * @param amountOfMonstersByCr amount of monsters in database by cr
     * @param monsters             monsters from database
     * @return hashmap with cr as key and list of monsters as value
     */

    private Map<Float, List<MonsterView>> convertMonsterQueryResultToMap(Map<Float, Integer> crs,
                                                                     Map<Float, Integer> amountOfMonstersByCr,
                                                                     List<MonsterView> monsters) {
        //sort monsters by cr and their amounts by cr
        // the array is then sliced to get the correct amount of monsters and put into the result hashmap
        monsters.sort(Comparator.comparing(MonsterView::getCr));
        HashMap<Float, List<MonsterView>> result = new HashMap<>();
        Float[] keys = crs.keySet().stream().sorted().toArray(Float[]::new);
        int index = 0;
        for (int i = 0; i < crs.size(); i++) {
            //if there are fewer monsters in database than requested, set amount to amount in database
            int amount = Math.min(crs.get(keys[i]), amountOfMonstersByCr.get(keys[i]));
            result.put(keys[i], monsters.subList(index, index + amount));
            index += amount;
        }

        return result;
    }

    /**
     * @param crs     amounts of requested monsters by cr
     * @param amounts amounts of monsters with the corresponding crs in the database in the same order as the crs
     * @return hashmap containing the amounts of monsters with the corresponding crs in the database
     */
    private Map<Float, Integer> convertCountQueryResultToMap(Map<Float, Integer> crs,
                                                             List<Integer> amounts) {
        HashMap<Float, Integer> result = new HashMap<>();
        Float[] keys = crs.keySet().toArray(new Float[0]);
        for (int i = 0; i < amounts.size(); i++) {
            if (amounts.get(i) == 0)
                throw new IllegalArgumentException("There are no monsters with cr = " + keys[i] + " in the database");
            result.put(keys[i], amounts.get(i));
        }

        return result;
    }

}
