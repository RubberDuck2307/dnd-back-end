package dnd.encounter_service.view.rabbitmq.monster.message;

import dnd.encounter_service.view.monster.entity.Monster;
import org.springframework.stereotype.Component;

@Component
public class MonsterMessageConvertor {

    public Monster parseMonsterMqCreate(MonsterMq monsterMqCreate){
        return new Monster(monsterMqCreate.getId(), monsterMqCreate.getName(), monsterMqCreate.getCr(), null);
    }

}
