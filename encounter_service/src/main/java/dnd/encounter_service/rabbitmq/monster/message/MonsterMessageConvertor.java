package dnd.encounter_service.rabbitmq.monster.message;

import dnd.encounter_service.view.monster.entity.MonsterView;
import org.springframework.stereotype.Component;

@Component
public class MonsterMessageConvertor {

    public MonsterView parseMonsterMqCreate(MonsterMq monsterMqCreate){

        return new MonsterView(monsterMqCreate.getId(), monsterMqCreate.getName(), monsterMqCreate.getCr(), null);
    }

}
