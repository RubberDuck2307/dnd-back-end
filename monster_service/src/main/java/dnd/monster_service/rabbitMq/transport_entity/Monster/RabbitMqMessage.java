package dnd.monster_service.rabbitMq.transport_entity.Monster;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RabbitMqMessage implements Serializable {
    private final String type;


    public RabbitMqMessage(String type) {
        this.type = type;
    }

}
