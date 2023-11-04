package dnd.rest_api.persistance.model.creature.monster.skills_of_monsters;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.persistance.model.creature.Skill;
import dnd.rest_api.persistance.model.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = SQLConfig.MONSTERS_SKILLS_TABLE, schema = SQLConfig.SCHEMA)

public class SkillsOfMonsters {

    @EmbeddedId
    private SkillsOfMonstersKey id;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne
    @MapsId("monsterId")
    @JoinColumn(name = "monster_id")
    private Monster monster;

    private short bonus;


}
