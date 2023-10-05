package dnd.RestApi.file.monster_json_to_sql;

import dnd.RestApi.file.monster_json_to_sql.MonsterJsonToSqlConverter;
import dnd.RestApi.file.monster_json_to_sql.SqlDataFileCreator;

import java.io.IOException;

public class SqlDataFileMain {

    public static void main(String[] args) throws IOException {
        MonsterJsonToSqlConverter monsterJsonToSqlConverter = new MonsterJsonToSqlConverter();
        SqlDataFileCreator sqlDataFileCreator = new SqlDataFileCreator();
        sqlDataFileCreator.writeLanguages(monsterJsonToSqlConverter.loadLanguages(monsterJsonToSqlConverter.loadMonsters()));

    }
}
