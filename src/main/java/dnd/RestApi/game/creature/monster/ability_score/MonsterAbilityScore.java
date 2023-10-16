package dnd.RestApi.game.creature.monster.ability_score;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.Ability;
import dnd.RestApi.game.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.ABILITY_SCORE_MONSTER_TABLE)
@Getter
@Setter
public class MonsterAbilityScore {

    @EmbeddedId
    private MonsterAbilityKey id;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    @MapsId("monsterId")
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "ability_id")
    @MapsId("abilityScoreId")
    private Ability abilityScore;

    private int value;


}
