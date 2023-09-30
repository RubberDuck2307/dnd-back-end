package dnd.RestApi.game.creature.monster;

import dnd.RestApi.game.creature.Creature;
import dnd.RestApi.game.creature.type.MonsterType;
import dnd.RestApi.game.dice.DiceRoll;
import dnd.RestApi.game.dice.DiceRolls;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Entity
@Table(schema = "dnd")
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
            schema = "dnd",
            name = "monster_type_relation",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<MonsterType> types;

    private Double cr;
    private String monsterName;
    private int averageHitPoints;
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private DiceRoll hitDice;
    //@TODO size

    @Builder
    public Monster(Set<MonsterType> type, Double cr, String monsterName, String size, String description,
                   String alignment) {
        super(size, description, alignment);
        this.types = type;
        this.cr = cr;
        this.monsterName = monsterName;
    }
}
