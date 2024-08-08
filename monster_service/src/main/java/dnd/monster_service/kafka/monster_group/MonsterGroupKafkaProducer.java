package dnd.monster_service.kafka.monster_group;

import com.fasterxml.jackson.databind.ObjectMapper;
import dnd.monster_service.config.KafkaConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import dnd.monster_service.rpc.server.MonsterRpcServer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import static dnd.monster_service.config.KafkaKeysConfig.NEW_MONSTER_GROUP_KEY;

@Component
@RequiredArgsConstructor
public class MonsterGroupKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfig kafkaConfig;
    private final ObjectMapper objectMapper;
    private final Logger logger = Logger.getLogger(MonsterRpcServer.class.getName());
    public void sendNewMonsterGroupMessage(MonsterGroup monsterGroup){
        NewMonsterGroupMessage message = new NewMonsterGroupMessage(monsterGroup.getId(),
                monsterGroup.getMonsters().stream().map(Monster::getId).collect(Collectors.toList()));
        String jsonValue;
        try {
            jsonValue = objectMapper.writeValueAsString(message);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(kafkaConfig.getMonsterGroupTopicName(), NEW_MONSTER_GROUP_KEY, jsonValue);
    }

}
