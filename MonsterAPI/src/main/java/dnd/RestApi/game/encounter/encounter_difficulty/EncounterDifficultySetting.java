package dnd.RestApi.game.encounter.encounter_difficulty;

import lombok.*;

import java.util.Map;

@Data
public class EncounterDifficultySetting {

    private  Map<EncounterDifficultyName, Integer> values;
    private  Integer level;


    public Integer getDifficulty(EncounterDifficultyName difficultyName) {
        return values.get(difficultyName);
    }

}
