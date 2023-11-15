package dnd.monster_service.rabbitMq.transport_entity.Monster;

import lombok.Getter;


@Getter
public class IdMessageMq extends RabbitMqMessage{

    private final long Id;
    public IdMessageMq(String type, long Id) {
        super(type);
        this.Id = Id;
    }
}
