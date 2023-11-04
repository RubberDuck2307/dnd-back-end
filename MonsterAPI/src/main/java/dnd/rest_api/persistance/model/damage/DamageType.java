package dnd.rest_api.persistance.model.damage;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.persistance.model.creature.monster.damage.MonsterDamage;
import dnd.rest_api.persistance.model.creature.monster.damage.MonsterVulnerability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = SQLConfig.DAMAGE_TYPE_TABLE, schema = SQLConfig.SCHEMA)
public class DamageType {
    @GeneratedValue
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "damageType", fetch = FetchType.LAZY)
    private Set<MonsterVulnerability> vulnerabilities;

    @OneToMany(mappedBy = "damageType", fetch = FetchType.LAZY)
    private Set<MonsterDamage> damageTakenModifiers;

}
