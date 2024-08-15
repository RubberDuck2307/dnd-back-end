package dnd.monster_service.persistance.entity.creature.monster;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.entity.creature.*;
import dnd.monster_service.persistance.entity.creature.monster.damage.MonsterDamage;
import dnd.monster_service.persistance.entity.creature.monster.damage.MonsterVulnerability;
import dnd.monster_service.persistance.entity.creature.monster.sense.MonsterSense;
import dnd.monster_service.persistance.entity.creature.monster.skills_of_monsters.SkillsOfMonsters;
import dnd.monster_service.persistance.entity.creature.type.MonsterType;
import dnd.monster_service.persistance.entity.creature.monster.ability_score.MonsterAbilityScore;
import dnd.monster_service.persistance.entity.creature.monster.ability_score.MonsterSavingThrow;
import dnd.monster_service.persistance.entity.creature.monster.speeds_of_monsters.SpeedsOfMonsters;
import dnd.monster_service.persistance.entity.dice.DiceRoll;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static dnd.monster_service.config.SQLConfig.SCHEMA;


@Entity
@Table(schema = SCHEMA)
@Getter
@Setter
@NoArgsConstructor

public class Monster{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Float cr;
    private String monsterName;
    private Short passivePerception;
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    private String legendaryActionDescription;
    private Boolean homebrew;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private CreatureSize size;
    private Short hitPoints;
    private Short ArmorClass;
    private String ArmorClassDescription;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = SCHEMA,
            name = SQLConfig.MONSTER_TYPE_RELATION_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<MonsterType> types;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterAbilityScore> monsterAbilityScores;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterSavingThrow> savingThrows;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SpeedsOfMonsters> speeds;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SkillsOfMonsters> skills;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterSense> senses;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterTrait> traits;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterAction> actions;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterReaction> reactions;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LegendaryAction> legendaryActions;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterDamage> damageResistancesAndImmunities;

    @OneToMany(mappedBy = "monster", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MonsterVulnerability> damageVulnerabilities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.MONSTER_LANGUAGE_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.MONSTER_CONDITION_IMMUNITY_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )
    private Set<Condition> conditionImmunities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            schema = SCHEMA,
            name = SQLConfig.MONSTER_GROUP_MONSTER_RELATION_TABLE,
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_group_id")
    )
    private Set<MonsterGroup> monsterGroups;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DiceRoll hitDice;

    public void setHitDice(DiceRoll hitDice) {
        this.hitDice = hitDice;
        setHitPoints((short) hitDice.getAverage());
    }

    public String toString() {
        return monsterName + " " + cr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        return id == monster.id;
    }

    public void setMonsterAbilityScores(Set<MonsterAbilityScore> abilities) {
        for (MonsterAbilityScore ability : abilities) {
            ability.setMonster(this);
        }
    }

    public void addMonsterGroup(MonsterGroup monsterGroup){
        monsterGroups.add(monsterGroup);
    }



}
