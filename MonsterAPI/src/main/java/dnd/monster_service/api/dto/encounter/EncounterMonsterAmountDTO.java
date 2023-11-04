package dnd.monster_service.api.dto.encounter;

import dnd.monster_service.api.dto.monster.MonsterShortGetDTO;
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
