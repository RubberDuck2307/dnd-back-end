package dnd.encounter_service.view.monster;

import dnd.encounter_service.view.ReadOnlyRepository;
import dnd.encounter_service.view.monster.entity.MonsterView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MonsterViewRepository extends ReadOnlyRepository<MonsterView, Long> {

    int countAllByCrIs(float cr);

    Page<MonsterView> getAllByCrIs(Float cr, Pageable pageable);

    @Query("SELECT distinct m.cr from MonsterView m join m.monsterGroups mg where mg.id.monsterGroupId = :id")
    List<Float> getAvailableCrForMonsterGroup(long id);


    Map<Float, List<MonsterView>> getMonstersByCrAndAmount(Map<Float, Integer> crs);

    Map<Float, List<MonsterView>> getMonstersByCrAmountAndMonsterGroupId(Map<Float, Integer> crs,
                                                                     long monsterGroupId);
    Map<Float, Integer> getAmountOfMonstersByCrAndMonsterGroup(Map<Float, Integer> crs, long monsterGroupId);

}
