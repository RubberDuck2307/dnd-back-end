package dnd.RestApi.game.creature.monster.damage;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.Skill;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.damage.DamageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.MONSTER_DAMAGE_TABLE)
public class MonsterDamage {
    @EmbeddedId
    private MonsterDamageKey id;
    private boolean isImmune;
    private boolean isVulnerable;
    private boolean isResistant;
    private boolean adamantineAttackFlag;
    private boolean silverAttackFlag;
    private boolean magicAttackFlag;

    @ManyToOne
    @MapsId("damageId")
    @JoinColumn(name = "damage_id")
    private DamageType damage;

    @ManyToOne
    @MapsId("monsterId")
    @JoinColumn(name = "monster_id")
    private Monster monster;



}
