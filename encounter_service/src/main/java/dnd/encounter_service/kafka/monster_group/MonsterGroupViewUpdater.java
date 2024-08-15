package dnd.encounter_service.kafka.monster_group;


import dnd.encounter_service.view.monster.MonsterViewRepository;
import dnd.encounter_service.view.monster.entity.MonsterGroupView;
import dnd.encounter_service.view.monster.entity.MonsterView;
import org.springframework.stereotype.Component;

@Component
public class MonsterGroupViewUpdater {

    private final MonsterGroupViewUpdateRepository updateRepository;
    private final MonsterViewRepository monsterRepository;

    MonsterGroupViewUpdater(MonsterGroupViewUpdateRepository updateRepository, MonsterViewRepository monsterRepository){
        this.updateRepository = updateRepository;
        this.monsterRepository = monsterRepository;
    }

    public void writeNewMonsterGroup(MonsterGroupMessage message) {
        message.monsterIds.forEach(id ->
        {
            MonsterView monster = monsterRepository.findById(id).orElseThrow();
            var monsterGroupViewId = new MonsterGroupView.MonsterGroupId(monster.getId(), message.monsterGroupId);
            MonsterGroupView monsterGroupView = new MonsterGroupView(monsterGroupViewId, monster);
            monster.addMonsterGroup(monsterGroupView);
            updateRepository.save(monsterGroupView);
        });
    }

    public void updateMonsterGroup(MonsterGroupMessage message){
        updateRepository.removeAllByMonsterGroupId(message.getMonsterGroupId());
        writeNewMonsterGroup(message);
    }
}
