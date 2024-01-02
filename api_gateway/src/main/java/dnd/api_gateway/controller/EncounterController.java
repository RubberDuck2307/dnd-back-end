package dnd.api_gateway.controller;

import dnd.api_gateway.adapter.EncounterService;
import dnd.api_gateway.dto.encounter.EncounterDTO;
import dnd.api_gateway.dto.encounter.EncounterListDTO;
import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/encounter")
@RequiredArgsConstructor
public class EncounterController {

    private final EncounterService encounterService;

    /**
     * @param xp                 Desired xp for the encounter
     * @param amountOfEncounters Amount of encounters to generate
     * @param xpTolerance        Tolerance for the xp
     * @param differentMonsters  If the encounter should have different monsters
     * @param maxMonsterAmount   Max amount of monsters in the encounter
     * @param monsterGroup       Specific monster group to generate the encounter from
     * @param maxDiversity       If false then the encounter will have only one kind of monster per cr. If true then the
     *                           encounter can have multiple kinds of monsters with the same cr
     */
    @GetMapping("/generated")
    public ResponseEntity<List<EncounterDTO>> getGeneratedEncounter(
            @RequestParam int xp,
            @RequestParam(required = false, defaultValue = "5") int amountOfEncounters,
            @RequestParam(required = false, defaultValue = "0.2") float xpTolerance,
            @RequestParam(required = false, defaultValue = "true") boolean differentMonsters,
            @RequestParam(required = false, defaultValue = "5") int maxMonsterAmount,
            @RequestParam(required = false, defaultValue = "0") long monsterGroup,
            @RequestParam(required = false, defaultValue = "false") boolean maxDiversity) {

        GenerateEncounterParams generateEncounterParams = new GenerateEncounterParams(xp, amountOfEncounters, xpTolerance,
                differentMonsters, maxMonsterAmount, !maxDiversity, monsterGroup);
        List<EncounterDTO> encounters = encounterService.generateEncounters(generateEncounterParams);
        return ResponseEntity.ok(encounters);
    }


}
