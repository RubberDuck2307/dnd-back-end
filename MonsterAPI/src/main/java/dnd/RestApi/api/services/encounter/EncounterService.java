package dnd.RestApi.api.services.encounter;

import dnd.RestApi.game.encounter.Encounter;
import dnd.RestApi.game.encounter.encounter_creating.EncounterCreationLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncounterService {

    private final EncounterCreationLogic encounterCreationLogic;

    public List<Encounter> getRandomEncounter(int xp) {
        return encounterCreationLogic.createRandomEncounter(xp, 5, 0.1F, true, 10);
    }


}
