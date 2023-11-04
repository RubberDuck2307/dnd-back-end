package dnd.rest_api.persistance.model.creature.monster.damage;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.persistance.model.creature.monster.Monster;
import dnd.rest_api.persistance.model.damage.AttackType;
import dnd.rest_api.persistance.model.damage.DamageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.MONSTER_DAMAGE_TABLE)
public class MonsterDamage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isImmune;

    @ManyToOne
    @JoinColumn(name = "attack_type_exception_id")
    private AttackType attackTypeException;

    @ManyToOne
    @JoinColumn(name = "damage_id")
    private DamageType damageType;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;



}
