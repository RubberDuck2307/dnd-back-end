package dnd.aiapi.services;

import dnd.aiapi.client.AiClient;
import dnd.aiapi.dtos.encounter.EncounterShortDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final AiClient aiClient;

    public String generateQuestIdeaOnEncounter(EncounterShortDTO encounter){

        StringBuilder encounterMonsters = new StringBuilder();
        encounter.getMonsters().forEach(monster -> {
            encounterMonsters.append(monster.getAmount());
            encounterMonsters.append("x");
            encounterMonsters.append(monster.getMonster().getName());
            encounterMonsters.append(" ");
        });

        return aiClient.getIdeaForQuestBasedOnEncounter(encounterMonsters.toString());
    }
}
