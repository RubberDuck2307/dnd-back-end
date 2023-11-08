package dnd.monster_service.persistance.entity.creature.monster.skills_of_monsters;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SkillsOfMonstersKey implements Serializable {

    @Column(name = "monster_id")
    private long monsterId;

    @Column(name = "skill_id")
    private long skillId;


}
