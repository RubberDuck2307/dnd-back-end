package dnd.RestApi.game.creature.monster.ability_score;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MonsterAbilityKey implements Serializable {

    @Column(name = "monster_id")
    private Long monsterId;
    @Column(name = "ability_id")
    private Long abilityScoreId;

}
