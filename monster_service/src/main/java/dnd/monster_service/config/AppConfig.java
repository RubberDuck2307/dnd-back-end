package dnd.monster_service.config;


import dnd.monster_service.game.logic.encounter.encounter_creating.DefaultEncounterCreationLogic;
import dnd.monster_service.game.logic.encounter.encounter_creating.EncounterCreationLogic;
import dnd.monster_service.game.logic.encounter.encounter_difficulty.EncounterDifficultyMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public EncounterCreationLogic encounterCreationLogic(EncounterDifficultyMap
            encounterDifficultyMap) {
        return new DefaultEncounterCreationLogic(encounterDifficultyMap);
    }


}
