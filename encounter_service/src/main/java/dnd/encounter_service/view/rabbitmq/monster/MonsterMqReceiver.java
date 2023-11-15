package dnd.encounter_service.view.rabbitmq.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dnd.encounter_service.view.rabbitmq.monster.message.MonsterMq;
import dnd.encounter_service.view.rabbitmq.monster.message.RabbitMqMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class MonsterMqReceiver {

    private final ObjectMapper objectMapper;
    private final MonsterViewConsumerService service;
    private final Logger logger = Logger.getLogger(MonsterMqReceiver.class.getName());
    @RabbitListener(queues = {"monsterCreated"})
    public void receiveMessage(String message) throws JsonProcessingException {
        logger.info("Received <" + message + ">");
        handleMessage(message);
    }

    private void handleMessage(String message) throws JsonProcessingException {
        TypeReference<RabbitMqMessage> mapType = new TypeReference<>(){};
        RabbitMqMessage rabbitMqMessage = objectMapper.readValue(message, mapType);

        switch (rabbitMqMessage.getType()){
            case "monsterCreated":
                TypeReference<MonsterMq> monsterMqCreateTypeReference = new TypeReference<>(){};
                MonsterMq monsterMqCreate = objectMapper.readValue(message, monsterMqCreateTypeReference);
                service.createMonster(monsterMqCreate);
                break;
            default:
                logger.warning("Unknown message type: " + rabbitMqMessage.getType());
        }


    }


}