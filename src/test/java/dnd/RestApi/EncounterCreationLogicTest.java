package dnd.RestApi;

import dnd.RestApi.exception.custom_exception.NoSuchEncounterException;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.creature.monster.MonsterNativeQueries;
import dnd.RestApi.game.creature.monster.MonsterRepository;
import dnd.RestApi.game.encounter.Encounter;
import dnd.RestApi.game.encounter.encounter_creating.EncounterCreationLogic;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EncounterCreationLogicTest {

    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    EncounterCreationLogic encounterCreationLogic;
    @Autowired
    EncounterDifficultyMap encounterDifficultyMap;
    @Autowired
    MonsterNativeQueries monsterNativeQueries;

    @Test
    public void getRandomEncounterOneEncounterOneMonsterTest() {
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(500,
                 1, 0.1F , false, 1);
       assertEquals(1, encounters.size());
       assertEquals(450,encounters.get(0).getDifficulty_xp());
    }

    @Test
    public void getRandomEncounterMultipleEncountersOneMonsterTest() {
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(200,
                50, 0.1F,false,  1);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficulty_xp() > 0 && encounter.getDifficulty_xp() <= 220);
            assertEquals(1, encounter.getMonsters().size());
        }
    }
    @Test
    public void getRandomEncounterNoSuchEncounterException(){
        assertThrows(NoSuchEncounterException.class, () -> encounterCreationLogic.createRandomEncounter(1,
                50, 0.1F,false,  1));
    }


    @Test public void getRandomEncounterMultipleEncountersDifferentMonstersTest(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(300,
                80, 1,true,  6);
        assertEquals(80, encounters.size());
        for (Encounter encounter : encounters) {
            System.out.println(encounter);
            assertTrue(encounter.getDifficulty_xp() > 0 && encounter.getDifficulty_xp() <= 600);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
        }

    }

}
