package dnd.monster_service.persistance.model.encounter;

import dnd.monster_service.game.logic.encounter.encounter_creating.EncounterCreationLogic;
import dnd.monster_service.persistance.model.creature.monster.Monster;
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
