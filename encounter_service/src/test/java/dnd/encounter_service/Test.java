package dnd.encounter_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    ObjectMapper objectMapper;

    @org.junit.jupiter.api.Test
    public void test() {
        String json = "{\"name\":\"John\", \"age\":30, \"car\":null}";
    }
}
