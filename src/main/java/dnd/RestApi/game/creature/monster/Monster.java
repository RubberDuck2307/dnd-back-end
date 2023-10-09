package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.Creature;
import dnd.RestApi.game.creature.Sense;
import dnd.RestApi.game.creature.monster.sense.MonsterSense;
import dnd.RestApi.game.creature.monster.skills_of_monsters.SkillsOfMonsters;
import dnd.RestApi.game.creature.monster.speeds_of_monsters.SpeedsOfMonsters;
import dnd.RestApi.game.creature.type.MonsterType;
import dnd.RestApi.game.dice.DiceRoll;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static dnd.RestApi.config.SQLConfig.SCHEMA;


@Entity
@Table(schema = SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Monster extends Creature {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = SCHEMA,
            name = SQLConfig.MONSTER_TYPE_RELATION_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<MonsterType> types;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SpeedsOfMonsters> speeds;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SkillsOfMonsters> skills;
    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterSense> senses;
    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Trait> traits;
    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Action> actions;
    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterReaction> reactions;
    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LegendaryAction> legendaryActions;
    private Double cr;
    private String monsterName;
    private Short strSavingThrowBonus;
    private Short dexSavingThrowBonus;
    private Short conSavingThrowBonus;
    private Short intSavingThrowBonus;
    private Short wisSavingThrowBonus;
    private Short chaSavingThrowBonus;
    private Short passivePerception;
    private String imageUrl;


    @OneToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    private DiceRoll hitDice;

    public void setHitDice(DiceRoll hitDice) {
        this.hitDice = hitDice;
        super.setHitPoints((short) hitDice.getAverage());
    }

}
