package dnd.monster_service.api.dto.ability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AbilityScoreShortGetDTO {

    private String title;
    private short value;



}