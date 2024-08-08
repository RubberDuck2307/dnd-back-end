package dnd.encounter_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dnd.encounter_service.logic.encounter_creation.DefaultEncounterCreationLogic;
import dnd.encounter_service.logic.encounter_creation.EncounterCreationLogic;
import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EncounterCreationLogic encounterCreationLogic(DifficultyService
                                                                 difficultyService) {
        return new DefaultEncounterCreationLogic(difficultyService);
    }

}

