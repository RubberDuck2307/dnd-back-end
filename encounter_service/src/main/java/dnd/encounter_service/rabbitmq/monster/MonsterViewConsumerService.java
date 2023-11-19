package dnd.encounter_service.rabbitmq.monster;

import dnd.encounter_service.view.monster.MonsterViewWriterRepository;
import dnd.encounter_service.view.monster.entity.MonsterView;
import dnd.encounter_service.rabbitmq.monster.message.MonsterMessageConvertor;
import dnd.encounter_service.rabbitmq.monster.message.MonsterMq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterViewConsumerService {

    private final MonsterViewWriterRepository monsterRepository;
    private final MonsterMessageConvertor monsterMessageConvertor;


    public void createMonster(MonsterMq monsterMq) {
        MonsterView monster = monsterMessageConvertor.parseMonsterMqCreate(monsterMq);
        monsterRepository.save(monster);
    }

}
