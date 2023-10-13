package dnd.RestApi.game.creature.monster.damage;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class MonsterDamageKey implements Serializable {
    @Column(name = "monster_id")
    private long monsterId;
    @Column(name = "damage_id")
    private long damageTypeId;
}
