package dnd.RestApi;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Order(1)
    public void fillInDatabase() {
        monsterRepository.save(
                Monster.builder()
                        .cr(1.0)
                        .monsterName("Goblin")
                        .build());
        monsterRepository.save(
                Monster.builder()
                        .cr(1.0)
                        .monsterName("Orc").build());
        monsterRepository.save(
                Monster.builder().cr(2.0).monsterName("Ogre").build());
    }


    @Test
    public void getRandomEncounterOneEncounterOneMonsterTest() {
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(500,
                 5, 0.1F , false, 1);
       assertEquals(1, encounters.size());
       assertEquals(450,encounters.get(0).getDifficulty_xp());
    }

    @Test
    public void getRandomEncounterMultipleEncountersOneMonsterTest() {
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(200,
                2, 0.1F,false,  1);
        assertEquals(2, encounters.size());
        assertEquals(200,encounters.get(0).getDifficulty_xp());
    }

    @Test public void getCrsForEncounterTest(){
        ArrayList<ArrayList<Double>> crsForEncounter = encounterCreationLogic.getCrsForEncounter(2000,
                90, encounterDifficultyMap.getCRs(), 0.3F);
        System.out.println(crsForEncounter);
    }

    @Test public void getCrsForEncounterTest2(){
        ArrayList<ArrayList<Double>> crsForEncounter = encounterCreationLogic.getCrsForEncounter(10000,
                10, encounterDifficultyMap.getCRs(), 0.2F);
        System.out.println(crsForEncounter);
    }

    @Test public void getRandomEncounterMultipleEncountersDifferentMonstersTest(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(300,
                2, 1,true,  3);
        System.out.println(encounters);

    }

}
