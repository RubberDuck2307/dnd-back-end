package dnd.RestApi.game.damage;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.monster.damage.MonsterDamage;
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

    @OneToMany(mappedBy = "damage", fetch = FetchType.LAZY)
    private Set<MonsterDamage> damageTakenModifiers;

}
