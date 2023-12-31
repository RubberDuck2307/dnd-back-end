package dnd.monster_service.persistance.entity.creature.monster.ability_score;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.Ability;
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

    private Short value;

    public MonsterAbilityScore() {
    }

    public MonsterAbilityScore(Ability abilityScore, Short value) {
        this.abilityScore = abilityScore;
        this.value = value;
    }

    public MonsterAbilityScore(Monster monster, Ability abilityScore, Short value) {
        this.monster = monster;
        this.abilityScore = abilityScore;
        this.value = value;
    }
}
