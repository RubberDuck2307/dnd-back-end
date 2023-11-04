package dnd.rest_api.persistance.model.creature.monster.sense;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.persistance.model.creature.Sense;
import dnd.rest_api.persistance.model.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = SQLConfig.MONSTER_SENSES_TABLE, schema = SQLConfig.SCHEMA)
public class MonsterSense {

    @EmbeddedId
    private MonsterSenseKey id;

    private short range;


    @ManyToOne
    @MapsId("senseId")
    @JoinColumn(name = "sense_id")
    private Sense sense;

    @ManyToOne
    @MapsId("monsterId")
    @JoinColumn(name = "monster_id")
    private Monster monster;
}
