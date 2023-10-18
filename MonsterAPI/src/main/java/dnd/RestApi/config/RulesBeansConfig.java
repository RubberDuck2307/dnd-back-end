package dnd.RestApi.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import dnd.RestApi.file.JsonFileReader;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
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
