package dnd.monster_service.rabbitMq.transport_entity.Monster;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MonsterMq.class, name = "monster"),
        @JsonSubTypes.Type(value = IdMessageMq.class, name = "id"),
        @JsonSubTypes.Type(value = GroupMq.class, name = "group"),
        @JsonSubTypes.Type(value = MonsterGroupMq.class, name = "monsterGroup")

})
public class RabbitMqMessage implements Serializable {
    private final String type;

    public RabbitMqMessage(String type) {
        this.type = type;
    }
}
