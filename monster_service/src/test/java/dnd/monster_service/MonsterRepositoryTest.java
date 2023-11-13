package dnd.monster_service;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.model.MonsterService;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import dnd.monster_service.persistance.entity.creature.type.MonsterType;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MonsterRepositoryTest {


    @Autowired
    MonsterService monsterService;

    @Test
    public void countMonstersByMonsterGroup() {
        assertEquals(5, monsterService.getAmountOfMonsterInGroup(1L));
    }

    @Test
    public void getMonsterByCr() {
        Map<Float, Integer> map = new HashMap<>();
        map.put(0.125F, 2);
        map.put(0.25F, 2);
        map.put(0.5F, 2);
        map.put(1F, 2);
        Map<Float, List<Monster>> monsters = monsterService.getMonstersByCr(map);
        assertEquals(2, monsters.get(0.125F).size());
        assertEquals(2, monsters.get(0.25F).size());
        assertEquals(2, monsters.get(0.5F).size());
        assertEquals(2, monsters.get(1F).size());
        monsters.keySet().forEach(key ->
                monsters.get(key).forEach(monster ->
                        assertEquals(monster.getCr(), key)));

    }

    @Test
    public void getMonstersFilteredByName() {
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter("Zombie", null, null, null);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Zombie")));

        monsterSearchFilter = new MonsterSearchFilter("Dragon", null, null, null);
        monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Dragon")));
        assertTrue(monsters.size() <= 10 && monsters.size() > 0);

    }

    @Test
    public void getMonsterFilteredByCr() {
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter(null, null, 0.125F, null);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertEquals(0.125F, m.getCr()));
        assertTrue(monsters.size() <= 10 && monsters.size() > 0);
    }

    @Test
    public void getMonsterFilteredByMonsterGroup() {
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter(null, null, null, 1L);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> isInGroup(1L, m));
        assertTrue(monsters.size() <= 10);
    }

    @Test
    public void getMonsterFilteredByType() {
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter(null, "undead", null, null);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> containsType("undead", m));
        assertTrue(monsters.size() <= 10);
    }

    @Test
    public void getMonsterFilteredByTypeNameCr(){
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter("Zombie", "evil", 0.25F, null);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Zombie")));
        monsters.forEach(m -> assertEquals(0.25F, m.getCr()));
        assertTrue(monsters.size() <= 10 && monsters.size() > 0);
    }

    @Test void getMonsterFilteredByNameCaseInsensitivity(){
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter("zOmBiE", null, null, null);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Zombie")));
        assertTrue(monsters.size() <= 10 && monsters.size() > 0);
    }

    @Test void getMonsterFilteredByTypeCaseInsensitivity(){
        MonsterSearchFilter monsterSearchFilter = new MonsterSearchFilter(null, "EvIl", null, null);
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> containsType("evil", m));
        assertTrue(monsters.size() <= 10);
    }

    private void containsType(String typeName, Monster m) {
        boolean contains = false;
        for (MonsterType type : m.getTypes()) {
            if (type.getName().contains(typeName)) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }

    private void isInGroup(long groupId, Monster m) {
        boolean contains = false;
        for (MonsterGroup gr : m.getMonsterGroups()) {
            if (gr.getId() == groupId) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }
}
