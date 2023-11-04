package dnd.rest_api.persistance.model.damage;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.persistance.model.creature.monster.damage.MonsterDamage;
import dnd.rest_api.persistance.model.creature.monster.damage.MonsterVulnerability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = SQLConfig.ATTACK_TYPE_TABLE, schema = SQLConfig.SCHEMA)
public class AttackType
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "attackType", fetch = FetchType.LAZY)
    private Set<MonsterVulnerability> vulnerabilities;

    @OneToMany(mappedBy = "attackTypeException", fetch = FetchType.LAZY)
    private Set<MonsterDamage> damageTakenModifiers;


}
