package dnd.encounter_service;


import dnd.encounter_service.exception.NoSuchEncounterException;
import dnd.encounter_service.logic.encounter_difficulty.EncounterDifficultyMap;
import dnd.encounter_service.model.Monster;
import dnd.encounter_service.model.encounter.Encounter;
import dnd.encounter_service.service.EncounterService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EncounterCreationLogicTest {

    @Autowired
    EncounterService encounterService;
    @Autowired
    EncounterDifficultyMap encounterDifficultyMap;

    @Test
    public void getRandomEncounterOneEncounterOneMonsterTest() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(500,
                1, 0.1F, false, 1);
        assertEquals(1, encounters.size());
        assertEquals(450, encounters.get(0).getDifficultyXp());
    }

    @Test
    public void getRandomEncounterMultipleEncountersOneMonsterTest() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(200,
                50, 0.1F, false, 1);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficultyXp() > 180 && encounter.getDifficultyXp() <= 220);
            assertEquals(1, encounter.getMonsters().size());
        }
    }

    @Test
    public void getRandomEncounterNoSuchEncounterException() {
        assertThrows(NoSuchEncounterException.class, () -> encounterService.createRandomEncounter(1,
                50, 0.1F, true, 2));
    }


    @Test
    public void getRandomEncounterMultipleEncountersDifferentMonstersTestOnlyOneMonsterPerCr() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(300,
                50, 0.5F, true, 6);
        assertEquals(50, encounters.size());
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficultyXp() >= 150 && encounter.getDifficultyXp() <= 450);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
            HashMap<Float, ArrayList<Monster>> monstersByCr = new HashMap<>();
            for (Monster monster : encounter.getMonsters()) {
                if (monstersByCr.containsKey(monster.getCr())) {
                    assertEquals(monster.getId(), monstersByCr.get(monster.getCr()).get(0).getId());
                } else {
                    ArrayList<Monster> monsters = new ArrayList<>();
                    monsters.add(monster);
                    monstersByCr.put(monster.getCr(), monsters);
                }
            }
        }
    }

    @Test
    public void getRandomEncounterMultipleEncounterMaxAmountOfMonsters() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(900,
                50, 0.2F, true, 99);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getMonsters().size() <= 10);
            assertTrue(encounter.getDifficultyXp() >= 720 && encounter.getDifficultyXp() <= 1080);
        }
    }

    @Test
    public void getRandomEncounterMultipleEncounterIgnoreSmallXp() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(6000,
                50, 0.2F, true, 10);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficultyXp() > 4800 && encounter.getDifficultyXp() <= 7200);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
            for (Monster monster : encounter.getMonsters()) {
                assertTrue(monster.getCr() >= 3);
            }
        }
    }

    @Test
    public void getRandomEncounterEdgeCase0Xp() {
        assertThrows(NoSuchEncounterException.class, () -> encounterService.createRandomEncounter(0,
                50, 0F, true, 10));
    }

    @Test
    public void getRandomEncounterEdgeCase0Monsters() {
        assertEquals(0, encounterService.createRandomEncounter(100,
                0, 0.1F, true, 10).size());
    }

    @Test
    public void getRandomEncounterEdgeCaseNegativeInputs() {
        assertThrows(IllegalArgumentException.class, () -> encounterService.createRandomEncounter(-1,
                50, 0.1F, true, 10));
        assertThrows(IllegalArgumentException.class, () -> encounterService.createRandomEncounter(100,
                -1, 0.1F, true, 10));
        assertThrows(IllegalArgumentException.class, () -> encounterService.createRandomEncounter(100,
                50, -0.1F, true, 10));
        assertThrows(IllegalArgumentException.class, () -> encounterService.createRandomEncounter(100,
                50, 0.1F, true, -1));
    }

    @Test
    public void getRandomEncounterOneTypeOfMonster() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(1000,
                50, 1F, false, 10);
        for (Encounter encounter : encounters) {
            Set<Long> monsters = new HashSet<>();
            for (Monster monster : encounter.getMonsters()) {
                monsters.add(monster.getId());
            }
            assertEquals(1, monsters.size());
            assertTrue(0 < encounter.getDifficultyXp() && encounter.getDifficultyXp() <= 2000);
        }
    }

    @Test
    public void getRandomEncounterMultipleEncountersDifferentMonstersMultipleMonsterPerCr() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(2400,
                200, 0.5F, true, 6,
                false, null);
        assertEquals(200, encounters.size());

        boolean difference = false;

        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficultyXp() >= 1200 && encounter.getDifficultyXp() <= 3600);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 6);
            HashMap<Float, ArrayList<Monster>> monstersByCr = new HashMap<>();
            for (Monster monster : encounter.getMonsters()) {
                if (monstersByCr.containsKey(monster.getCr())) {
                    if (monster.getId() != monstersByCr.get(monster.getCr()).get(0).getId())
                        difference = true;
                } else {
                    ArrayList<Monster> monsters = new ArrayList<>();
                    monsters.add(monster);
                    monstersByCr.put(monster.getCr(), monsters);
                }
            }
        }
        assertTrue(difference);
    }

    @Test
    public void testGetRandomEncounterMultipleEncountersDifferentMonstersMultipleMonsterPerCrMonsterGroup() {
        ArrayList<Encounter> encounters = encounterService.createRandomEncounter(600,
                10, 1, true, 4,
                false, 1L);
        assertEquals(10, encounters.size());
        System.out.println(encounters);
        for (Encounter encounter : encounters) {
            assertTrue(encounter.getDifficultyXp() > 0 && encounter.getDifficultyXp() <= 1200);
            assertTrue(encounter.getMonsters().size() > 0 && encounter.getMonsters().size() <= 4);
            for (Monster monster: encounter.getMonsters()){
                Long[] monsterIdsInMonsterGroup = new Long[]{218L,215L,145L,311L,151L};
                assertTrue(Arrays.asList(monsterIdsInMonsterGroup).contains(monster.getId()));
            }
        }
    }


}
