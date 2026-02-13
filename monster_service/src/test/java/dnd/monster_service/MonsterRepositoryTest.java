package dnd.monster_service;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.service.MonsterService;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import dnd.monster_service.persistance.entity.creature.type.MonsterType;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MonsterRepositoryTest {


    @Autowired
    MonsterService monsterService;


    @Test
    public void getMonstersFilteredByName() {
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().name("Zombie").build();
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Zombie")));

        monsterSearchFilter = MonsterSearchFilter.builder().name("Dragon").build();
        monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Dragon")));
        assertTrue(monsters.size() <= 10 && !monsters.isEmpty());

    }

    @Test
    public void getMonsterFilteredByCr() {
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().minCR(0.125F).build();
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertEquals(0.125F, m.getCr()));
        assertTrue(monsters.size() <= 10 && !monsters.isEmpty());
    }

    @Test
    public void getMonsterFilteredByMonsterGroup() {
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().groupId(1L).build();
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> isInGroup(1L, m));
        assertTrue(monsters.size() <= 10);
    }

    @Test
    public void getMonsterFilteredByType() {
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().type("undead").build();
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> containsType("undead", m));
        assertTrue(monsters.size() <= 10);
    }

    @Test
    public void getMonsterFilteredByTypeNameCr(){
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().name("Zombie").type("evil").minCR(0.25F).build();
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Zombie")));
        monsters.forEach(m -> assertEquals(0.25F, m.getCr()));
        assertTrue(monsters.size() <= 10 && !monsters.isEmpty());
    }

    @Test void getMonsterFilteredByNameCaseInsensitivity(){
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().name("zOmBiE").build();
        List<Monster> monsters = monsterService.getMonsters(10, 0, monsterSearchFilter);
        monsters.forEach(m -> assertTrue(m.getMonsterName().contains("Zombie")));
        assertTrue(monsters.size() <= 10 && !monsters.isEmpty());
    }

    @Test void getMonsterFilteredByTypeCaseInsensitivity(){
        MonsterSearchFilter monsterSearchFilter = MonsterSearchFilter.builder().type("EvIl").build();
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
