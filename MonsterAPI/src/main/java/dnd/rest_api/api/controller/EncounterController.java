package dnd.rest_api.api.controller;

import dnd.rest_api.api.dto.encounter.EncounterDTOMapper;
import dnd.rest_api.api.dto.encounter.EncounterShortGetDTO;
import dnd.rest_api.persistance.model.encounter.Encounter;
import dnd.rest_api.persistance.service.interfaces.EncounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${url.versioning}/encounter")
@RequiredArgsConstructor
public class EncounterController {

    private final EncounterService encounterService;
    private final EncounterDTOMapper encounterDTOMapper;

    @GetMapping("/random")
    public ResponseEntity<List<EncounterShortGetDTO>> getRandomEncounter(@RequestParam int xp) {
        List<Encounter> encounters = encounterService.createRandomEncounter(xp, 5, 0.2f, true, 10);
        return new ResponseEntity<>(encounters.stream().map(encounterDTOMapper::getEncounterShortGetDTO).toList()
                , HttpStatus.OK);
    }

}