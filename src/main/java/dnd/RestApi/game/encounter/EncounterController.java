package dnd.RestApi.game.encounter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.versioning}/encounter")
public class EncounterController {


    /**
     * Returns an encounter suitable for specified party and difficulty.
     */

    @GetMapping("/party")
    public ResponseEntity<String> getEncounterByParty() {

        return ResponseEntity.ok("Encounter");
    }

}
