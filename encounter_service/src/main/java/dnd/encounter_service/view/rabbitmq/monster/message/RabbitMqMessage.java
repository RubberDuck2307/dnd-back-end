package dnd.encounter_service.view.rabbitmq.monster.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.io.Serializable;


@Getter
public class RabbitMqMessage implements Serializable {
    private final String type;

    public RabbitMqMessage(String type) {
        this.type = type;
    }
}
