package dnd.RestApi.file.monster_json_to_sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dnd.RestApi.file.JsonFileReader;
import dnd.RestApi.file.MonsterJson;
import dnd.RestApi.game.creature.monster.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MonsterJsonToSqlConverter {

    private final JsonFileReader fileReader;
    private static final String MONSTERS_FILE_PATH = "src/main/resources/monsters.json";

    public MonsterJsonToSqlConverter() {
        ObjectMapper objectMapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();
        this.fileReader = new JsonFileReader(null, objectMapper, new File(MONSTERS_FILE_PATH));
    }

    public List<MonsterJson> loadMonsters() throws FileNotFoundException, JsonProcessingException {
        List<MonsterJson> monsterJsons = fileReader.readMonstersJson();
        return monsterJsons;
    }

    public Set<String> loadMonsterSize(List<MonsterJson> monsterJsons) {
        Set<String> sizes = new HashSet<>();
        monsterJsons.forEach(
                monsterJson -> {
                    String meta = monsterJson.getMeta();
                    meta = meta.trim();
                    String size = meta.substring(0, meta.indexOf(" "));
                    sizes.add(size);
                });
        return sizes;
    }

    public Set<String> loadSenses(List<MonsterJson> monsterJsons) {
        Set<String> senses = new HashSet<>();
        monsterJsons.forEach(monsterJson -> {
            String sensesString = monsterJson.getSenses();
            if (sensesString != null) {
                sensesString = sensesString.trim();
                sensesString = sensesString.replace(" ft.", "");
                sensesString = sensesString.replaceAll("([0-9]+)", "");
                String[] sensesArray = sensesString.split(",");
                Arrays.stream(sensesArray).forEach(sense ->
                        senses.add(sense.trim().substring(0, 1).toUpperCase() + sense.trim().substring(1)));
            }
        });
        return senses;
    }

    public Set<String> loadLanguages(List<MonsterJson> monsterJsons) {
        Set<String> languages = new HashSet<>();
        monsterJsons.forEach(monsterJson -> {
            String languagesString = monsterJson.getLanguages();
            if (languagesString != null) {
                languagesString = languagesString.trim();
                languagesString = languagesString.replace(" and ", ",");
                String[] languagesArray = languagesString.split(",");
                Arrays.stream(languagesArray).forEach(language -> {
                    String languageString = language.trim();
                    languageString = languageString.substring(0, 1).toUpperCase() + languageString.substring(1);
                    languageString = languageString.replace("'", "''");
                    languages.add(languageString);
                });
            }
        });
        return languages;

    }

    public Set<String> loadMonsterType(List<MonsterJson> monsterJsons) {
        Set<String> types = new HashSet<>();
        monsterJsons.forEach(
                monsterJson -> {
                    String meta = monsterJson.getMeta();
                    meta = meta.trim();
                    String typesString = meta.substring(meta.indexOf(" ") + 1);
                    String[] typesArray = typesString.split(",");
                    Arrays.stream(typesArray).forEach(type ->
                            types.add(type.trim().substring(0, 1).toUpperCase() + type.trim().substring(1)));
                });
        return types;
    }

}
