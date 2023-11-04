package dnd.rest_api.api.dto.skill;

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

}
