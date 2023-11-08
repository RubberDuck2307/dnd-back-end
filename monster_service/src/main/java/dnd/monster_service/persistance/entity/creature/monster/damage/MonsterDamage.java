package dnd.monster_service.persistance.entity.creature.monster.damage;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.damage.AttackType;
import dnd.monster_service.persistance.entity.damage.DamageType;
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
