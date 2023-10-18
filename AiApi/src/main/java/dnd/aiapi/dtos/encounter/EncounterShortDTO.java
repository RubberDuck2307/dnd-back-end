package dnd.aiapi.dtos.encounter;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class EncounterShortDTO {

    private List<EncounterMonsterAmountDTO> monsters;
    private int difficulty_xp;
    private int gained_xp;


}
