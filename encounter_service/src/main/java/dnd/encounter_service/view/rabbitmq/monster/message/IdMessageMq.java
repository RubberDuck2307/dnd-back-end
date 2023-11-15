package dnd.encounter_service.view.rabbitmq.monster.message;

import lombok.Getter;


@Getter
public class IdMessageMq extends RabbitMqMessage{

    private final long Id;
    public IdMessageMq(String type, long Id) {
        super(type);
        this.Id = Id;
    }
}
