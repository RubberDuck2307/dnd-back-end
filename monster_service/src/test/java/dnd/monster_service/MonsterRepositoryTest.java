package dnd.monster_service;

import dnd.monster_service.persistance.repository.monster.MonsterRepository;
import dnd.monster_service.persistance.service.interfaces.MonsterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MonsterRepositoryTest {


    @Autowired
    MonsterService monsterService;

    @Test
    public void countMonstersByMonsterGroup(){
        assertEquals(5 , monsterService.getAmountOfMonsterInGroup(1L));
    }
}
