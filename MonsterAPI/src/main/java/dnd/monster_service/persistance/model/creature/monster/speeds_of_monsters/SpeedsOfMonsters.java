package dnd.monster_service.persistance.model.creature.monster.speeds_of_monsters;

import dnd.monster_service.persistance.model.creature.monster.Monster;
import dnd.monster_service.persistance.model.creature.Speed;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static dnd.monster_service.config.SQLConfig.SCHEMA;
import static dnd.monster_service.config.SQLConfig.SPEED_OF_MONSTERS_TABLE;

@Entity
@Table(schema = SCHEMA, name = SPEED_OF_MONSTERS_TABLE)
@Getter
@Setter
public class SpeedsOfMonsters {
    @EmbeddedId
    private SpeedsOfMonstersKey id;

    @ManyToOne
    @MapsId("speedId")
    @JoinColumn(name = "speed_id")
    private Speed speed;

    @ManyToOne
    @MapsId("monsterId")
    @JoinColumn(name = "monster_id")
    private Monster monster;

    private short value;
}
