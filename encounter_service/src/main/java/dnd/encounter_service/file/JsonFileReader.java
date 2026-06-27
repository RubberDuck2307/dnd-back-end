package dnd.encounter_service.file;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dnd.encounter_service.logic.encounter_difficulty.DefaultDifficultyService;
import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class JsonFileReader {

    public final static String encounterDifficultySetting = "encounter_difficulty.json";

    private final ObjectMapper objectMapper;

    public JsonFileReader() {
        this.objectMapper = JsonMapper.builder()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
                .build();
    }

    public DifficultyService readEncounterDifficultyMap() throws IOException {
        ClassPathResource resource = new ClassPathResource(encounterDifficultySetting);

        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, DefaultDifficultyService.class);
        }
    }
}