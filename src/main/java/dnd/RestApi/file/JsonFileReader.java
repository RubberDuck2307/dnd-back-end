package dnd.RestApi.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dnd.RestApi.game.encounter.encounter_difficulty.DefaultEncounterDifficultyMap;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Component
public class JsonFileReader {

    private final File encounterDifficultyFile;
    private final ObjectMapper objectMapper;
    private final File monstersFile;

    @Autowired
    public JsonFileReader(@Qualifier("encounterDifficultyFile") File encounterDifficultyFile, ObjectMapper objectMapper,
                          @Qualifier("monstersFile") File monstersFile) {
        this.encounterDifficultyFile = encounterDifficultyFile;
        this.objectMapper = objectMapper;
        this.monstersFile = monstersFile;
    }

    public EncounterDifficultyMap readEncounterDifficultyMap() throws FileNotFoundException, JsonProcessingException {
        String json = readFile(encounterDifficultyFile);
        return objectMapper.readValue(json, DefaultEncounterDifficultyMap.class);
    }

    public List<MonsterJson> readMonstersJson() throws FileNotFoundException, JsonProcessingException {
        String json = readFile(monstersFile);
        return objectMapper.readValue(json, objectMapper.getTypeFactory().
                constructCollectionType(List.class, MonsterJson.class));
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
