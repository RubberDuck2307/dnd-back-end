package dnd.RestApi.api.repositories.monster;

import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.creature.monster.MonsterGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

    Long countAllByCrIs(Double cr);

    Optional<Monster> findByMonsterNameIgnoreCase(String name);
    Page<Monster> getAllByCrIs(Double cr, Pageable pageable);
    HashMap<Double, List<Monster>> getMonstersByCrAndAmount(HashMap<Double, Integer> crs);

    HashMap<Double, List<Monster>> getMonstersByCrAmountAndMonsterGroupId(HashMap<Double, Integer> crs,
                                                                           Long monsterGroupId);
    @Query("SELECT distinct m.cr FROM MonsterGroup mg JOIN mg.monsters m WHERE mg.id = :id")
    List<Double> getAllCrWhereMonsterGroupsContaining_Id(Long id);
}
