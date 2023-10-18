package dnd.RestApi.game.creature.monster.speeds_of_monsters;

import dnd.RestApi.game.creature.Speed;
import dnd.RestApi.game.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static dnd.RestApi.config.SQLConfig.SCHEMA;
import static dnd.RestApi.config.SQLConfig.SPEED_OF_MONSTERS_TABLE;

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
