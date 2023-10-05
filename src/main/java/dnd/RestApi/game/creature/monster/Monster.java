package dnd.RestApi.game.creature.monster;

import dnd.RestApi.game.creature.Creature;
import dnd.RestApi.game.creature.creature_size.CreatureSize;
import dnd.RestApi.game.creature.type.MonsterType;
import dnd.RestApi.game.dice.DiceRoll;
import dnd.RestApi.game.dice.DiceRolls;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static dnd.RestApi.config.SQLConfig.schema;

@AllArgsConstructor
@Entity
@Table(schema = schema)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Monster extends Creature {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = schema,
            name = "monster_type_relation",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<MonsterType> types;

    private Double cr;
    private String monsterName;
    private int averageHitPoints;
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private DiceRoll hitDice;
    public void setHitDice(DiceRoll hitDice) {
        this.hitDice = hitDice;
        this.averageHitPoints = hitDice.getAverage();
    }
}
