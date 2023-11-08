package dnd.monster_service.persistance.entity.creature.monster.sense;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MonsterSenseKey implements Serializable {

    @Column(name = "monster_id")
    private long monsterId;

    @Column(name = "sense_id")
    private long senseId;

}
