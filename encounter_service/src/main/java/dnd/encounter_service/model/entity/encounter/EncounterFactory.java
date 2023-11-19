package dnd.encounter_service.model.entity.encounter;


import dnd.encounter_service.logic.encounter_creation.EncounterCreationLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class EncounterFactory {

    private final EncounterCreationLogic encounterCreationLogic;

    public Encounter createEncounter(List<Monster> monsters){

        List<Float> crs = new ArrayList<>();
        monsters.forEach(monster -> crs.add(monster.getCr()));

        return new Encounter( new ArrayList<>(monsters), encounterCreationLogic.calculateEncounterGainedXp(crs),
                encounterCreationLogic.calculateEncounterDifficultyXp(crs));
    }
}
