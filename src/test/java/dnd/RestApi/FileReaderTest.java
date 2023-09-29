package dnd.RestApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dnd.RestApi.file.JsonFileReader;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class FileReaderTest {

    @Autowired
    EncounterDifficultyMap encounterDifficultiesMap;
    @Autowired
    JsonFileReader jsonFileReader;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void isEncounterDifficultiesMapCorrectlyLoaded() {
        assertEquals(25, encounterDifficultiesMap.getXpThreshold(1, EncounterDifficultyName.EASY));
        assertEquals(25, encounterDifficultiesMap.getXp(0.125));
        assertEquals(1.5, encounterDifficultiesMap.getMultiplier(2));
    }

    @Test
    public void isMonstersJsonCorrectlyLoaded() throws Exception{
        System.out.println(jsonFileReader.readMonstersJson());
    }


}
