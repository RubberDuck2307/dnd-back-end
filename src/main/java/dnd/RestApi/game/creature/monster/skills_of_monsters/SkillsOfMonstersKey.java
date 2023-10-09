package dnd.RestApi.game.creature.monster.skills_of_monsters;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SkillsOfMonstersKey implements Serializable {

    @Column(name = "monster_id")
    private long monsterId;

    @Column(name = "skill_id")
    private long skillId;


}
