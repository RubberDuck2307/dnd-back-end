package dnd.encounter_service.rabbitmq.monster.message;

import dnd.encounter_service.rabbitmq.shared.RabbitMqMessage;
import lombok.Getter;

@Getter
public class GroupMq extends RabbitMqMessage {

    private final long id;
    private final String name;

    public GroupMq(String type, long id, String name) {
        super(type);
        this.id = id;
        this.name = name;
    }
}
