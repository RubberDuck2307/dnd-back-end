package dnd.RestApi.game.creature.monster;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
// TODO change to repository
public class MonsterNativeQueries {

    private final EntityManager entityManager;

    @Autowired
    public MonsterNativeQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public HashMap<Double, List<Monster>> getMonstersByCrAndAmount(HashMap<Double, Integer> crs) {
        Session session = entityManager.unwrap(Session.class);

        if (crs.isEmpty())
            return new HashMap<>();

        StringBuilder countQueryString = new StringBuilder("SELECT COUNT(*)\n" +
                "from dnd.monster\n" +
                "where cr = :cr1");

        for (int i = 1; i < crs.size(); i++) {
            countQueryString.append("\n UNION ALL SELECT COUNT(*)\n" +
                    "from dnd.monster\n" +
                    "where cr = :cr").append(i + 1);
        }

        Query<Integer> countQuery = session.createNativeQuery(countQueryString.toString(), Integer.class);

        Double[] crsKeyArray = crs.keySet().toArray(new Double[0]);
        for (int i = 0; i < crs.size(); i++) {
            countQuery.setParameter("cr" + (i + 1), crsKeyArray[i]);
        }

        HashMap<Double, Integer> amountOfMonstersByCr = convertCountQueryResultToHashMap(crs, countQuery.getResultList());

        StringBuilder queryString = new StringBuilder("(SELECT *\n" +
                "from dnd.monster\n" +
                "where cr = :cr1\n" +
                "OFFSET floor(random() * :amt1)\n " +
                "LIMIT :lmt1)");

        for (int i = 1; i < crs.size(); i++) {
            queryString.append("\nUNION (SELECT *\n" +
                    "from dnd.monster\n" +
                    "where cr = :cr").append(i + 1).append("\n" +
                    "OFFSET floor(random() * :amt").append(i + 1).append(")\n" +
                    "LIMIT :lmt").append(i + 1).append(")");
        }

        Query<Monster> query = session.createNativeQuery(queryString.toString(), Monster.class);

        for (int i = 0; i < crs.size(); i++) {
            int amt = Math.max(amountOfMonstersByCr.get(crsKeyArray[i]) - crs.get(crsKeyArray[i]), 0);
            query.setParameter("cr" + (i + 1), crsKeyArray[i]);
            query.setParameter("lmt" + (i + 1), crs.get(crsKeyArray[i]));
            query.setParameter("amt" + (i + 1), amt);
        }

        List<Monster> monsterList = query.getResultList();
        session.close();
        HashMap<Double, List<Monster>> monsters = convertMonsterQueryResultToHashMap(crs, amountOfMonstersByCr,
                monsterList);
        return monsters;
    }


    private HashMap<Double, List<Monster>> convertMonsterQueryResultToHashMap(HashMap<Double, Integer> crs,
                                                                              HashMap<Double, Integer> amountOfMonstersByCr,
                                                                              List<Monster> monsters) {
        monsters.sort(Comparator.comparing(Monster::getCr));
        HashMap<Double, List<Monster>> result = new HashMap<>();
        Double[] keys = crs.keySet().toArray(new Double[0]);
        int index = 0;
        for (int i = 0; i < crs.size(); i++) {
            int amount = Math.min(crs.get(keys[i]), amountOfMonstersByCr.get(keys[i]));
            result.put(keys[i], monsters.subList(index, index + amount));
                index += amount;
        }

        return result;
    }

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
