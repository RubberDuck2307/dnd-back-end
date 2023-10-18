package dnd.RestApi.game.creature.monster.damage;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.damage.AttackType;
import dnd.RestApi.game.damage.DamageType;
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
