package dnd.monster_service.persistance.repository.monster;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.model.cr.CrRange;
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
    public List<Monster> getMonstersFiltered(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter)
    {
        return getMonstersFiltered(pageSize, pageNumber, monsterSearchFilter, new MonsterSearchSorting(true,"NAME"));
    }

    public long countMonstersFiltered(MonsterSearchFilter monsterSearchFilter) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Monster> root = criteriaQuery.from(Monster.class);

        List<Predicate> predicates = buildPredicates(criteriaBuilder, root, monsterSearchFilter);

        criteriaQuery.select(criteriaBuilder.count(root)).where(predicates.toArray(new Predicate[0]));
        Query<Long> query = session.createQuery(criteriaQuery);
        long result = query.getSingleResult();
        session.close();
        return result;
    }

    public List<Monster> getMonstersFiltered(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter,
                                             MonsterSearchSorting sorting) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Monster> criteriaQuery = criteriaBuilder.createQuery(Monster.class);
        Root<Monster> root = criteriaQuery.from(Monster.class);

        List<Predicate> predicates = buildPredicates(criteriaBuilder, root, monsterSearchFilter);

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

        if (sorting.isAscending()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sorting.getSortingType().getMatchingField())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sorting.getSortingType().getMatchingField())));
        }
        List<Monster> result = session.createQuery(criteriaQuery).setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize).getResultList();
        session.close();
        return result;
    }


    private List<Predicate> buildPredicates(CriteriaBuilder criteriaBuilder, Root<Monster> root,
                                            MonsterSearchFilter monsterSearchFilter)
    {
        List<Predicate> predicates = new ArrayList<>();

        if (monsterSearchFilter.getName() != null && !monsterSearchFilter.getName().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(
                    root.get("monsterName")), "%" + monsterSearchFilter.getName().toLowerCase() + "%"));
        }

        if (monsterSearchFilter.getMinCR() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("cr"), monsterSearchFilter.getMinCR()));
        }

        if (monsterSearchFilter.getMaxCR() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("cr"), monsterSearchFilter.getMaxCR()));
        }

        if (monsterSearchFilter.getGroupId() != null) {
            Join<Monster, MonsterGroup> monsterGroupMonsterRelationJoin = root.join("monsterGroups");
            predicates.add(criteriaBuilder.equal(monsterGroupMonsterRelationJoin.get("id"),
                    monsterSearchFilter.getGroupId()));
        }
        if (monsterSearchFilter.getType() != null) {
            Join<Monster, MonsterType> monsterTypeJoin = root.join("types");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(monsterTypeJoin.get("name"))
                    , "%" + monsterSearchFilter.getType().toLowerCase() + "%"));
        }
        return predicates;
    }

//    public CrRange getCrRange() {
//        Session session = entityManager.unwrap(Session.class);
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Float> criteriaQuery = criteriaBuilder.createQuery(Float.class);
//        Root<Monster> root = criteriaQuery.from(Monster.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(criteriaBuilder.equal(root.get("cr"), criteriaBuilder.least(root.get("cr").as(Float.class))));
//        predicates.add(criteriaBuilder.equal(root.get("cr"), criteriaBuilder.greatest(root.get("cr").as(Float.class))));
//        criteriaQuery.select(root.get("cr")).where(predicates.toArray(new Predicate[0]));
//        List<Float> result = session.createQuery(criteriaQuery).getResultList();
//        return  new CrRange(Collections.min(result), Collections.max(result));
//    }
}
