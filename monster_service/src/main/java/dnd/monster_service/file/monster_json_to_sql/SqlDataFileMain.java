package dnd.monster_service.file.monster_json_to_sql;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dnd.monster_service.file.JsonFileReader;

import java.io.File;

import java.io.IOException;

public class SqlDataFileMain {

    private static final String MONSTERS_FILE_PATH = "src/main/resources/monsters.json";


    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();
        JsonFileReader fileReader = new JsonFileReader(null, objectMapper, new File(MONSTERS_FILE_PATH));

        MonsterJsonToSqlConverter monsterJsonToSqlConverter = new MonsterJsonToSqlConverter();
        SqlDataFileCreator sqlDataFileCreator = new SqlDataFileCreator(monsterJsonToSqlConverter);
        sqlDataFileCreator.writeAll(fileReader.readMonstersJson(), fileReader.readConditions());

    }
}
