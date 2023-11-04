package dnd.encounter_service.logic.encounter_difficulty;

import lombok.Data;

import java.util.Map;

@Data
public class EncounterDifficultySetting {

    private  Map<EncounterDifficultyName, Integer> values;
    private  Integer level;


    public Integer getDifficulty(EncounterDifficultyName difficultyName) {
        return values.get(difficultyName);
    }

}
