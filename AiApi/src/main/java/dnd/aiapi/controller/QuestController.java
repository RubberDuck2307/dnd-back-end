package dnd.aiapi.controller;

import dnd.aiapi.dtos.QuestIdeaDTO;
import dnd.aiapi.dtos.encounter.EncounterShortDTO;
import dnd.aiapi.services.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/quest")
public class QuestController {

    private final QuestService questService;


    @PostMapping("/")
    public ResponseEntity<QuestIdeaDTO> generateQuestIdeaOnEncounter(@RequestBody EncounterShortDTO dto){
        System.out.println(dto);
        return ResponseEntity.ok(new QuestIdeaDTO(questService.generateQuestIdeaOnEncounter(dto)));
    }
}
