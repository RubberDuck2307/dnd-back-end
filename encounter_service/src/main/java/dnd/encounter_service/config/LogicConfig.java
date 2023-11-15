package dnd.encounter_service.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import dnd.encounter_service.file.JsonFileReader;
import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

@RequiredArgsConstructor
@Configuration
public class LogicConfig {

    private final JsonFileReader fileReader;

    @Bean
    public DifficultyService encounterDifficultyMap() throws FileNotFoundException, JsonProcessingException {
        return fileReader.readEncounterDifficultyMap();
    }

}
