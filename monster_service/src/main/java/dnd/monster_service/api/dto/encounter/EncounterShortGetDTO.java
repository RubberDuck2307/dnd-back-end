package dnd.monster_service.api.dto.encounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class EncounterShortGetDTO {

    private List<EncounterMonsterAmountDTO> monsters;
    private int difficultyXp;
    private int gainedXp;



}
