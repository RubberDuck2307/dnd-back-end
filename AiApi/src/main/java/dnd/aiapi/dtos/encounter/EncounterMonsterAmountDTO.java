package dnd.aiapi.dtos.encounter;

import dnd.aiapi.dtos.monster.MonsterShortGetDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EncounterMonsterAmountDTO {

    private MonsterShortGetDTO monster;
    private int amount;

}
