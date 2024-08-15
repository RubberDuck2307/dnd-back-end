package dnd.monster_service.kafka.monster_group;

import dnd.monster_service.config.KafkaConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import dnd.monster_service.rpc.server.MonsterRpcServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import static dnd.monster_service.config.KafkaKeysConfig.NEW_MONSTER_GROUP_KEY;
import static dnd.monster_service.config.KafkaKeysConfig.UPDATED_MONSTER_GROUP_KEY;

@Component
@RequiredArgsConstructor
public class MonsterGroupKafkaProducer {

    private final KafkaProducer producer;
    private final KafkaConfig kafkaConfig;
    private final Logger logger = Logger.getLogger(MonsterRpcServer.class.getName());
    public void sendNewMonsterGroupMessage(MonsterGroup monsterGroup){
        MonsterGroupMessage message = new MonsterGroupMessage(monsterGroup.getId(),
                monsterGroup.getMonsters().stream().map(Monster::getId).collect(Collectors.toList()));
        producer.sendMessage(message, NEW_MONSTER_GROUP_KEY, kafkaConfig.getMonsterGroupTopicName());
    }

    public void sendMonsterGroupEditedMessage(MonsterGroup monsterGroup){
        MonsterGroupMessage message = new MonsterGroupMessage(monsterGroup.getId(),
                monsterGroup.getMonsters().stream().map(Monster::getId).collect(Collectors.toList()));
        producer.sendMessage(message, UPDATED_MONSTER_GROUP_KEY, kafkaConfig.getMonsterGroupTopicName());
    }

}
