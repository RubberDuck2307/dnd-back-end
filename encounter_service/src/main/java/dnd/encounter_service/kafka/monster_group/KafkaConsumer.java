package dnd.encounter_service.kafka.monster_group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static dnd.encounter_service.config.KafkaKeyConfig.NEW_MONSTER_GROUP_KEY;
import static dnd.encounter_service.config.KafkaKeyConfig.UPDATED_MONSTER_GROUP_KEY;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {


    private final ObjectMapper objectMapper;
    private final MonsterGroupViewUpdater updater;
    private final Logger logger = Logger.getLogger(KafkaConsumer.class.getName());


    @KafkaListener(topics = "${spring.kafka.monstergroup.topic}")
    public void receiveMonsterGroupTopicMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        logger.info(String.format("Received key:%s message: %s",key, message));
        switch (key) {
            case (NEW_MONSTER_GROUP_KEY) -> {
                var parsedMessage = parseMessage(message, MonsterGroupMessage.class);
                updater.writeNewMonsterGroup(parsedMessage);
            }
            case (UPDATED_MONSTER_GROUP_KEY) -> {
                var parsedMessage = parseMessage(message, MonsterGroupMessage.class);
                updater.updateMonsterGroup(parsedMessage);
            }

        }

    }


    private <T> T parseMessage(String message, Class<T> valueType) {
        try {
            return objectMapper.readValue(message, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
