package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.damage.DamageType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Creature {
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private CreatureSize size;
    private Short hitPoints;
    private Short ArmorClass;
    private String ArmorClassDescription;
    private Short strength;
    private Short dexterity;
    private Short constitution;
    private Short intelligence;
    private Short wisdom;
    private Short charisma;
    @ManyToMany
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.CREATURE_DAMAGE_RESISTANCE_TABLE,
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name = "damage_type_id")
    )
    private  Set<DamageType> damageResistances;
    @ManyToMany
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.CREATURE_DAMAGE_IMMUNITY_TABLE,
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name = "damage_type_id")
    )
    private Set<DamageType> damageImmunities;
    @ManyToMany
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.CREATURE_CONDITION_IMMUNITY_TABLE,
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )
    private Set<DamageType> conditionImmunities;
    @ManyToMany
    @JoinTable(
            schema = SQLConfig.SCHEMA,
            name = SQLConfig.CREATURE_LANGUAGE_TABLE,
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages;


}
