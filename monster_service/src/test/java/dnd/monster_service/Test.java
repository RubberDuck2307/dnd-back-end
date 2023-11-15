package dnd.monster_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dnd.monster_service.model.MonsterService;
import dnd.monster_service.model.PostgresMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {

    @Autowired
    private PostgresMonsterService monsterService;

    @org.junit.jupiter.api.Test
    public void test() throws JsonProcessingException {
        monsterService.addMonster();
    }

}
