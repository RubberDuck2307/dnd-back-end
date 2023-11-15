package dnd.monster_service.rabbitMq.transport_entity.Monster;

import lombok.Getter;

@Getter
public class MonsterGroupMq  extends RabbitMqMessage{

    private final long monsterId;
    private final long groupId;

    public MonsterGroupMq(String type, long monsterId, long groupId) {
        super(type);
        this.monsterId = monsterId;
        this.groupId = groupId;
    }
}
