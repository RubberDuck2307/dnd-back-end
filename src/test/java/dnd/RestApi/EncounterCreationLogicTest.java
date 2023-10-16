package dnd.RestApi;

import dnd.RestApi.exception.custom_exception.NoSuchEncounterException;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.api.repositories.monster.MonsterRepository;
import dnd.RestApi.game.encounter.Encounter;
import dnd.RestApi.game.encounter.encounter_creating.EncounterCreationLogic;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
                50, 0.1F,true,  2));
    }


    @Test public void getRandomEncounterMultipleEncountersDifferentMonstersTestOnlyOneMonsterPerCr(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(300,
                50, 1,true,  6);
        assertEquals(50, encounters.size());
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficulty_xp() > 0 && encounter.getDifficulty_xp() <= 600);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
            HashMap<Double, ArrayList<Monster>>  monstersByCr = new HashMap<>();
            for (Monster monster : encounter.getMonsters()) {
                if(monstersByCr.containsKey(monster.getCr())){
                    assertEquals(monster.getId(), monstersByCr.get(monster.getCr()).get(0).getId());
                }else{
                    ArrayList<Monster> monsters = new ArrayList<>();
                    monsters.add(monster);
                    monstersByCr.put(monster.getCr(), monsters);
                }
            }
        }
    }

    @Test
    public void getRandomEncounterMultipleEncounterMaxAmountOfMonsters(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(900,
                50, 0.2F,true,  99);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getMonsters().size() <= 10);
            assertTrue(encounter.getDifficulty_xp() >= 720 && encounter.getDifficulty_xp() <= 1080);
        }
    }

    @Test public void getRandomEncounterMultipleEncounterIgnoreSmallXp(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(6000,
                50, 0.2F,true,  10);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficulty_xp() > 4800 && encounter.getDifficulty_xp() <= 7200);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
            for (Monster monster : encounter.getMonsters()) {
                assertTrue(monster.getCr() >= 3);
            }
        }
    }

    @Test public void getRandomEncounterEdgeCase0Xp(){
        assertThrows(NoSuchEncounterException.class, () -> encounterCreationLogic.createRandomEncounter(0,
                50, 0F,true,  10));
    }

    @Test public void getRandomEncounterEdgeCase0Monsters(){
        assertEquals(0, encounterCreationLogic.createRandomEncounter(100,
                0, 0.1F,true,  10).size());
    }

    @Test public void getRandomEncounterEdgeCaseNegativeInputs(){
        assertThrows(IllegalArgumentException.class, () -> encounterCreationLogic.createRandomEncounter(-1,
                50, 0.1F,true,  10));
        assertThrows(IllegalArgumentException.class, () -> encounterCreationLogic.createRandomEncounter(100,
                -1, 0.1F,true,  10));
        assertThrows(IllegalArgumentException.class, () -> encounterCreationLogic.createRandomEncounter(100,
                50, -0.1F,true,  10));
        assertThrows(IllegalArgumentException.class, () -> encounterCreationLogic.createRandomEncounter(100,
                50, 0.1F,true,  -1));
    }

    @Test public void getRandomEncounterOneTypeOfMonster(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(1000,
                50, 1F,false,  10);
        for (Encounter encounter : encounters) {
            Set<Long> monsters = new HashSet<>();
            for (Monster monster : encounter.getMonsters()) {
                monsters.add(monster.getId());
            }
            assertEquals(1, monsters.size());
            assertTrue(0 < encounter.getDifficulty_xp() && encounter.getDifficulty_xp() <= 2000);
        }
    }

    @Test public void getRandomEncounterMultipleEncountersDifferentMonstersTestOnlyMultipleMonsterPerCr(){
        ArrayList<Encounter> encounters = encounterCreationLogic.createRandomEncounter(300,
                200, 1,true,  6, false);
        assertEquals(200, encounters.size());

        boolean difference = false;

        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficulty_xp() > 0 && encounter.getDifficulty_xp() <= 600);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
            HashMap<Double, ArrayList<Monster>>  monstersByCr = new HashMap<>();
            for (Monster monster : encounter.getMonsters()) {
                if(monstersByCr.containsKey(monster.getCr())){
                    if(monster.getId() != monstersByCr.get(monster.getCr()).get(0).getId())
                        difference = true;
                }else{
                    ArrayList<Monster> monsters = new ArrayList<>();
                    monsters.add(monster);
                    monstersByCr.put(monster.getCr(), monsters);
                }
            }
        }
        assertTrue(difference);
    }





}
