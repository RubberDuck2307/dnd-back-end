package dnd.monster_service.rabbitMq.transport_entity.Monster;

import lombok.Getter;

@Getter
public class GroupMq extends RabbitMqMessage{

    private final long id;
    private final String name;

    public GroupMq(String type, long id, String name) {
        super(type);
        this.id = id;
        this.name = name;
    }
}
