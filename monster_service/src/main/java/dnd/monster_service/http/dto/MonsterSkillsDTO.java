package dnd.monster_service.http.dto;

import lombok.Data;

@Data

public class MonsterSkillsDTO {

    public MonsterSkillsDTO(String skillName, Short bonus) {
        this.skillName = skillName;
        this.bonus = bonus;
    }

    private String skillName;
    private Short bonus;

}
