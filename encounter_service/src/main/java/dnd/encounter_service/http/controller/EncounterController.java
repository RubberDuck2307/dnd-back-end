package dnd.encounter_service.http.controller;

import dnd.encounter_service.http.controller.dto.EncounterDTO;
import dnd.encounter_service.http.controller.dtomapper.EncounterDtoMapper;
import dnd.encounter_service.model.entity.encounter.Encounter;
import dnd.encounter_service.service.interfaces.EncounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class EncounterController {


    private final EncounterService service;
    private final EncounterDtoMapper dtoMapper;

    @GetMapping("/")
    ResponseEntity<List<EncounterDTO>> generateEncounter
            (@RequestParam(required = true) int desiredXp,
             @RequestParam(required = false, defaultValue = "10") Integer amountOfEncounters,
             @RequestParam(required = false, defaultValue = "false") Boolean oneMonsterKind,
             @RequestParam(required = false, defaultValue = "0.5") Float xpTolerance,
             @RequestParam(required = false, defaultValue = "5") Integer maxAmountOfMonster,
             @RequestParam(required = false, defaultValue = "true") Boolean oneCrMonster
            ) {
        List<Encounter> result = service.createRandomEncounter(desiredXp, amountOfEncounters,
                xpTolerance, !oneMonsterKind, maxAmountOfMonster, oneCrMonster, null);

        return ResponseEntity.ok(dtoMapper.buildEncounterDtoList(result));
    }

}
