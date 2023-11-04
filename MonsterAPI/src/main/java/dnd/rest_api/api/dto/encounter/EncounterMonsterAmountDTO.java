package dnd.rest_api.api.dto.encounter;

import dnd.rest_api.api.dto.monster.MonsterShortGetDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EncounterMonsterAmountDTO {

    private MonsterShortGetDTO monster;
    private int amount;


}
