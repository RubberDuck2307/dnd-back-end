package dnd.monster_service.persistance.repository.monster;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import dnd.monster_service.persistance.entity.creature.type.MonsterType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class contains implementation of methods from MonsterRepository. That were too complex to be implemented in the interface.
 */
@Component
public class MonsterRepositoryImpl {

    private final EntityManager entityManager;

    @Autowired
    public MonsterRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * fetches monsters from the database based on the search filters.
     * @param pageSize Specifies the amount of monsters per page
     * @param pageNumber Specifies the page that should be returned
     * @param monsterSearchFilter Specifies the search filters, if a filter is null it will be ignored. If any attribute
     *                           of the filter is null the attribute will be ignored.
     * @return List of monsters that match the search filters
     */
    public List<Monster> getMonstersFiltered(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Monster> criteriaQuery = criteriaBuilder.createQuery(Monster.class);
        Root<Monster> root = criteriaQuery.from(Monster.class);

        List<Predicate> predicates = new ArrayList<>();

        if (monsterSearchFilter.name() != null && !monsterSearchFilter.name().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(
                    root.get("monsterName")), "%" + monsterSearchFilter.name().toLowerCase() + "%"));
        }

        if (monsterSearchFilter.cr() != null) {
            predicates.add(criteriaBuilder.equal(root.get("cr"), monsterSearchFilter.cr()));
        }

        if (monsterSearchFilter.groupId() != null) {
            Join<Monster, MonsterGroup> monsterGroupMonsterRelationJoin = root.join("monsterGroups");
            predicates.add(criteriaBuilder.equal(monsterGroupMonsterRelationJoin.get("id"),
                    monsterSearchFilter.groupId()));
        }
        if (monsterSearchFilter.type() != null) {
            Join<Monster, MonsterType> monsterTypeJoin = root.join("types");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(monsterTypeJoin.get("name"))
                    , "%" + monsterSearchFilter.type().toLowerCase() + "%"));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        List<Monster> result = session.createQuery(criteriaQuery).setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize).getResultList();
        session.close();
        return result;
    }

}
