package dnd.monster_service.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import dnd.monster_service.file.JsonFileReader;
import dnd.monster_service.game.logic.encounter.encounter_difficulty.EncounterDifficultyMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

/**
 * This class specify beans that contains the game rules constants.
 */

@RequiredArgsConstructor
@Configuration
public class RulesBeansConfig {

    private final JsonFileReader fileReader;

    @Bean
    public EncounterDifficultyMap encounterDifficultyMap() throws FileNotFoundException, JsonProcessingException {
        return fileReader.readEncounterDifficultyMap();
    }
}
