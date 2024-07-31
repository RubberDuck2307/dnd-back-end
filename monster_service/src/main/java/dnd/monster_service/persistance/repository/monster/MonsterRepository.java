package dnd.monster_service.persistance.repository.monster;

import dnd.monster_service.model.cr.CrRange;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

    int countAllByCrIs(Double cr);

    /**
     * fetches monsters from the database based on the search filters.
     * @param pageSize Specifies the amount of monsters per page
     * @param pageNumber Specifies the page that should be returned
     * @param monsterSearchFilter Specifies the search filters, if a filter is null it will be ignored. If any attribute
     *                           of the filter is null the attribute will be ignored.
     * @return List of monsters that match the search filters
     */
    List<Monster> getMonstersFiltered(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter);

    List<Monster> getMonstersFiltered(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter, MonsterSearchSorting sorting);
    @Query("select count(m) from Monster m join m.monsterGroups mg where mg.id = :id")
    int countByMonsterGroupId(long id);

    long countMonstersFiltered(MonsterSearchFilter monsterSearchFilter);

    @Query("select  min(m.cr) from Monster m")
    Float getMinCr();

    @Query("select  max(m.cr) from Monster m")
    Float getMaxCr();
}
