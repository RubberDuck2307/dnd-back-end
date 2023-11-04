package dnd.monster_service.persistance.repository.monster;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.model.creature.monster.Monster;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
// TODO change to repository
public class MonsterRepositoryImpl {

    private final EntityManager entityManager;

    @Autowired
    public MonsterRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     * @param crs Map where the keys represent the cr and the values the amount of monsters with that cr that should be returned
     * @return HashMap containing monsters with the corresponding crs as keys
     */
    public HashMap<Double, List<Monster>> getMonstersByCrAndAmount(HashMap<Double, Integer> crs) {
        return getMonstersByCrAmountAndMonsterGroupId(crs, null);
    }

    public HashMap<Double, List<Monster>> getMonstersByCrAmountAndMonsterGroupId(HashMap<Double, Integer> crs,
                                                                                 Long monsterGroupId) {
        Session session = entityManager.unwrap(Session.class);

        if (crs.isEmpty())
            return new HashMap<>();

        StringBuilder countQueryString = new StringBuilder("SELECT COUNT(*)\n" +
                "from dnd.monster\n" +
                "where cr = :cr1\n");

        addMonsterGroupCondition(monsterGroupId, countQueryString);


        for (int i = 1; i < crs.size(); i++) {
            countQueryString.append("\n UNION ALL SELECT COUNT(*)\n" +
                    "from dnd.monster\n" +
                    "where cr = :cr").append(i + 1).append("\n");
            addMonsterGroupCondition(monsterGroupId, countQueryString);
        }

        Query<Integer> countQuery = session.createNativeQuery(countQueryString.toString(), Integer.class);

        Double[] crsKeyArray = crs.keySet().toArray(new Double[0]);
        for (int i = 0; i < crs.size(); i++) {
            countQuery.setParameter("cr" + (i + 1), crsKeyArray[i]);
        }
        if (monsterGroupId != null)
            countQuery.setParameter("monsterGroupId", monsterGroupId);

        //get amount of monster in database by cr
        HashMap<Double, Integer> amountOfMonstersByCr = convertCountQueryResultToHashMap(crs, countQuery.getResultList());

        StringBuilder queryString = new StringBuilder("(SELECT *\n" +
                "from dnd.monster\n" +
                "where cr = :cr1\n");

        addMonsterGroupCondition(monsterGroupId, queryString);

        queryString.append("OFFSET floor(random() * :amt1)\n " +
                "LIMIT :lmt1)");

        for (int i = 1; i < crs.size(); i++) {
            queryString.append("\nUNION (SELECT *\n" +
                    "from dnd.monster\n" +
                    "where cr = :cr").append(i + 1).append("\n");

            addMonsterGroupCondition(monsterGroupId, queryString);

            queryString.append("OFFSET floor(random() * :amt").append(i +1).append(")\n" +
                    "LIMIT :lmt").append(i + 1).append(")");
        }

        Query<Monster> query = session.createNativeQuery(queryString.toString(), Monster.class);

        for (int i = 0; i < crs.size(); i++) {
            int amt = Math.max(amountOfMonstersByCr.get(crsKeyArray[i]) - crs.get(crsKeyArray[i]), 0);
            query.setParameter("cr" + (i + 1), crsKeyArray[i]);
            query.setParameter("lmt" + (i + 1), crs.get(crsKeyArray[i]));
            //set maximum possible offset. If there are fewer monsters in database than requested, set offset to 0
            query.setParameter("amt" + (i + 1), amt);
        }
        if (monsterGroupId != null) {
            query.setParameter("monsterGroupId", monsterGroupId);
        }

        List<Monster> monsterList = query.getResultList();
        session.close();
        HashMap<Double, List<Monster>> monsters = convertMonsterQueryResultToHashMap(crs, amountOfMonstersByCr,
                monsterList);
        return monsters;
    }

    private void addMonsterGroupCondition(Long monsterGroupId, StringBuilder countQueryString) {
        if (monsterGroupId != null) {
            countQueryString.append("AND id IN (SELECT monster_id\n" +
                    "FROM ")
                    .append(SQLConfig.SCHEMA).append(".")
                    .append(SQLConfig.MONSTER_GROUP_MONSTER_RELATION_TABLE)
                    .append("\nWHERE monster_group_id = :monsterGroupId)\n");
        }
    }

    /**
     * Converts the result of the query to a hashmap with cr as key and list of monsters as value
     * @param crs amounts of requested monsters by cr
     * @param amountOfMonstersByCr amount of monsters in database by cr
     * @param monsters monsters from database
     * @return hashmap with cr as key and list of monsters as value
     */

    private HashMap<Double, List<Monster>> convertMonsterQueryResultToHashMap(HashMap<Double, Integer> crs,
                                                                              HashMap<Double, Integer> amountOfMonstersByCr,
                                                                              List<Monster> monsters) {
        //sort monsters by cr and their amounts by cr
        // the array is then sliced to get the correct amount of monsters and put into the result hashmap
        monsters.sort(Comparator.comparing(Monster::getCr));
        HashMap<Double, List<Monster>> result = new HashMap<>();
        Double[] keys =  crs.keySet().stream().sorted().toArray(Double[]::new);
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
     *
     * @param crs amounts of requested monsters by cr
     * @param amounts amounts of monsters with the corresponding crs in the database in the same order as the crs
     * @return hashmap containing the amounts of monsters with the corresponding crs in the database
     */
    private HashMap<Double, Integer> convertCountQueryResultToHashMap(HashMap<Double, Integer> crs,
                                                                      List<Integer> amounts) {
        HashMap<Double, Integer> result = new HashMap<>();
        Double[] keys = crs.keySet().toArray(new Double[0]);
        for (int i = 0; i < amounts.size(); i++) {
            if (amounts.get(i) == 0)
                throw new IllegalArgumentException("There are no monsters with cr = " + keys[i] + " in the database");
            result.put(keys[i], amounts.get(i));
        }

        return result;
    }

}
