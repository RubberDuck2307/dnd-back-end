package dnd.encounter_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dnd.encounter_service.file.JsonFileReader;
import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import dnd.encounter_service.logic.encounter_difficulty.EncounterDifficultyName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class FileReaderTest {

    @Autowired
    DifficultyService encounterDifficultiesMap;
    @Autowired
    JsonFileReader jsonFileReader;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void isEncounterDifficultiesMapCorrectlyLoaded() {
        assertEquals(25, encounterDifficultiesMap.getXpThreshold(1, EncounterDifficultyName.EASY));
        assertEquals(25, encounterDifficultiesMap.getXp(0.125F));
        assertEquals(1.5, encounterDifficultiesMap.getMultiplier(2));
    }



}
