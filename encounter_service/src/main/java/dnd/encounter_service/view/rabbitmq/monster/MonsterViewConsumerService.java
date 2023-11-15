package dnd.encounter_service.view.rabbitmq.monster;

import dnd.encounter_service.view.monster.entity.Monster;
import dnd.encounter_service.view.rabbitmq.monster.message.MonsterMessageConvertor;
import dnd.encounter_service.view.rabbitmq.monster.message.MonsterMq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterViewConsumerService {

    private final MonsterViewWriterRepository monsterRepository;
    private final MonsterMessageConvertor monsterMessageConvertor;


    public void createMonster(MonsterMq monsterMq) {
        Monster monster = monsterMessageConvertor.parseMonsterMqCreate(monsterMq);
        monsterRepository.save(monster);
    }

}
