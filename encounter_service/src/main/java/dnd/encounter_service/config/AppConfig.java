package dnd.encounter_service.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dnd.encounter_service.logic.encounter_creation.DefaultEncounterCreationLogic;
import dnd.encounter_service.logic.encounter_creation.EncounterCreationLogic;
import dnd.encounter_service.logic.encounter_difficulty.EncounterDifficultyMap;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EncounterCreationLogic encounterCreationLogic(EncounterDifficultyMap
                                                                 encounterDifficultyMap) {
        return new DefaultEncounterCreationLogic(encounterDifficultyMap);
    }
}

