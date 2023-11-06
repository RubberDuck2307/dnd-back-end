package dnd.encounter_service.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dnd.encounter_service.logic.encounter_difficulty.DefaultEncounterDifficultyMap;
import dnd.encounter_service.logic.encounter_difficulty.EncounterDifficultyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class JsonFileReader {

    public final static String encounterDifficultySetting = "src/main/resources/encounter_difficulty.json";

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonFileReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public EncounterDifficultyMap readEncounterDifficultyMap() throws FileNotFoundException, JsonProcessingException {
        String json = readFile(new File(encounterDifficultySetting));
        return objectMapper.readValue(json, DefaultEncounterDifficultyMap.class);
    }

    private String readFile(File file) throws FileNotFoundException {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }
}
