package dnd.monster_service.persistance.repository.monster;

import dnd.monster_service.persistance.model.creature.monster.Monster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

    int countAllByCrIs(Double cr);
    Optional<Monster> findByMonsterNameIgnoreCase(String name);
    Page<Monster> getAllByCrIs(Double cr, Pageable pageable);
    Map<Float, List<Monster>> getMonstersByCrAndAmount(Map<Float, Integer> crs);

    Map<Float, List<Monster>> getMonstersByCrAmountAndMonsterGroupId(Map<Float, Integer> crs,
                                                                          Long monsterGroupId);
    Map<Float, Integer> getAmountOfMonstersByCrAndMonsterGroup(Map<Float, Integer> crs, Long monsterGroupId);
    @Query("select count(m) from Monster m join m.monsterGroups mg where mg.id = :id")
    int countByMonsterGroupId(Long id);
    @Query("SELECT distinct m.cr FROM MonsterGroup mg JOIN mg.monsters m WHERE mg.id = :id")
    List<Float> getAllCrWhereMonsterGroupsContaining_Id(Long id);
}