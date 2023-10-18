package dnd.RestApi.api.controllers;

import dnd.RestApi.api.dtos.encounter.EncounterShortGetDTO;
import dnd.RestApi.api.services.encounter.EncounterService;
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

    @GetMapping("/random")
    public ResponseEntity<List<EncounterShortGetDTO>> getRandomEncounter(@RequestParam int xp) {

        return new ResponseEntity<>(encounterService.getRandomEncounter(xp).stream()
                .map(EncounterShortGetDTO::new).toList(), HttpStatus.OK);
    }

}
