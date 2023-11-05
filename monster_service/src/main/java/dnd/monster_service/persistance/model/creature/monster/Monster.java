package dnd.monster_service.persistance.model.creature.monster;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.model.creature.monster.damage.MonsterDamage;
import dnd.monster_service.persistance.model.creature.monster.damage.MonsterVulnerability;
import dnd.monster_service.persistance.model.creature.monster.sense.MonsterSense;
import dnd.monster_service.persistance.model.creature.monster.skills_of_monsters.SkillsOfMonsters;
import dnd.monster_service.persistance.model.creature.type.MonsterType;
import dnd.monster_service.persistance.model.creature.Condition;
import dnd.monster_service.persistance.model.creature.Creature;
import dnd.monster_service.persistance.model.creature.Language;
import dnd.monster_service.persistance.model.creature.monster.ability_score.MonsterAbilityScore;
import dnd.monster_service.persistance.model.creature.monster.ability_score.MonsterSavingThrow;
import dnd.monster_service.persistance.model.creature.monster.speeds_of_monsters.SpeedsOfMonsters;
import dnd.monster_service.persistance.model.dice.DiceRoll;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static dnd.monster_service.config.SQLConfig.SCHEMA;


@Entity
@Table(schema = SCHEMA)
@Getter
@Setter
@NoArgsConstructor

public class Monster extends Creature{

    @Id
    @GeneratedValue
    private long id;
    private Float cr;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            schema = SCHEMA,
            name = SQLConfig.MONSTER_GROUP_MONSTER_RELATION_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_group_id")
    )
    private Set<MonsterGroup> monsterGroups;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DiceRoll hitDice;

    public void setHitDice(DiceRoll hitDice) {
        this.hitDice = hitDice;
        super.setHitPoints((short) hitDice.getAverage());
    }

    public String toString(){
        return monsterName + " " + cr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        return id == monster.id;
    }



}
