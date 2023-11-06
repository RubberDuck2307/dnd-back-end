package dnd.monster_service;

import dnd.monster_service.persistance.model.creature.monster.Monster;
import dnd.monster_service.persistance.service.MonsterService;
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

}
