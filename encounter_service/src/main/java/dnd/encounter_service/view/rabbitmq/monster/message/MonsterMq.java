package dnd.encounter_service.view.rabbitmq.monster.message;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MonsterMq extends RabbitMqMessage{

    private final long Id;
    private final String name;
    private final float cr;
    private final boolean homebrew;

    public MonsterMq(long Id, String name, float cr, String type, boolean homebrew) {
        super(type);
        this.Id = Id;
        this.name = name;
        this.cr = cr;
        this.homebrew = homebrew;
    }
}
