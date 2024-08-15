package dnd.monster_service.kafka.monster_group;

import com.fasterxml.jackson.databind.ObjectMapper;
import dnd.monster_service.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static dnd.monster_service.config.KafkaKeysConfig.NEW_MONSTER_GROUP_KEY;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(Object object, String key, String topic){
        String jsonValue;
        try {
            jsonValue = objectMapper.writeValueAsString(object);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(topic, key, jsonValue);
    }
}
