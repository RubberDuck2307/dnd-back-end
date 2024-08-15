package dnd.encounter_service.kafka.monster_group;

import dnd.encounter_service.view.monster.entity.MonsterGroupView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonsterGroupViewUpdateRepository extends JpaRepository<MonsterGroupView, MonsterGroupView.MonsterGroupId> {

    @Modifying
    @Transactional
    @Query("DELETE from MonsterGroupView where id.monsterGroupId = :id")
    int removeAllByMonsterGroupId(@Param("id")Long monsterGroupId);

}
