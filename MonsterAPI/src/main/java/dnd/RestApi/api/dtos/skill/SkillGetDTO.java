package dnd.RestApi.api.dtos.skill;

import dnd.RestApi.game.creature.monster.skills_of_monsters.SkillsOfMonsters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SkillGetDTO {

    private String skill;
    private short bonus;
    private String ability;

    public SkillGetDTO(SkillsOfMonsters skillOfMonster){
        this.skill = skillOfMonster.getSkill().getName();
        this.bonus = skillOfMonster.getBonus();
        this.ability = skillOfMonster.getSkill().getAbility().getTitle();
    }
}
