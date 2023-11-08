package dnd.monster_service.persistance.entity.creature.monster.ability_score;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.Ability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = SQLConfig.MONSTER_SAVING_THROWS_TABLE, schema = SQLConfig.SCHEMA)
public class MonsterSavingThrow {
    @EmbeddedId
    private MonsterAbilityKey id;

    private Short value;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    @MapsId("monsterId")
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "ability_id")
    @MapsId("abilityScoreId")
    private Ability abilityScore;

}
