package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.Condition;
import dnd.RestApi.game.creature.Creature;
import dnd.RestApi.game.creature.Language;
import dnd.RestApi.game.creature.monster.ability_score.MonsterAbilityScore;
import dnd.RestApi.game.creature.monster.ability_score.MonsterSavingThrow;
import dnd.RestApi.game.creature.monster.damage.MonsterDamage;
import dnd.RestApi.game.creature.monster.damage.MonsterVulnerability;
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

public class Monster extends Creature {

    @Id
    @GeneratedValue
    private long id;
    private Double cr;
    private String monsterName;
    private Short passivePerception;
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    private String legendaryActionDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = SCHEMA,
            name = SQLConfig.MONSTER_TYPE_RELATION_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<MonsterType> types;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterAbilityScore> monsterAbilityScores;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterSavingThrow> savingThrows;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SpeedsOfMonsters> speeds;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SkillsOfMonsters> skills;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterSense> senses;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterTrait> traits;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterAction> actions;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterReaction> reactions;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LegendaryAction> legendaryActions;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterDamage> damageResistancesAndImmunities;

    @OneToMany(mappedBy = "monster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MonsterVulnerability> damageVulnerabilities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.MONSTER_LANGUAGE_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.MONSTER_CONDITION_IMMUNITY_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )
    private Set<Condition> conditionImmunities;



    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DiceRoll hitDice;

    public void setHitDice(DiceRoll hitDice) {
        this.hitDice = hitDice;
        super.setHitPoints((short) hitDice.getAverage());
    }

    public String toString(){
        return monsterName + " " + cr;
    }





}
