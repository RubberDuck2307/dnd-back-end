package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.model.entity.encounter.Monster;
import org.springframework.stereotype.Component;

@Component
public class MonsterViewMapper {


    public Monster ViewToModelMonster(MonsterView monsterView){
        return new Monster(monsterView.getId(), monsterView.getName(), monsterView.getCr());
    }
}
