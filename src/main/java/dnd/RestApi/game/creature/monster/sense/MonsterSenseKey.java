package dnd.RestApi.game.creature.monster.sense;

import dnd.RestApi.game.creature.Skill;
import dnd.RestApi.game.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class MonsterSenseKey implements Serializable {

    @Column(name = "monster_id")
    private long monsterId;

    @Column(name = "sense_id")
    private long senseId;

}
