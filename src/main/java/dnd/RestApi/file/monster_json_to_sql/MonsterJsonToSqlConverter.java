package dnd.RestApi.file.monster_json_to_sql;

import dnd.RestApi.file.MonsterJson;
import dnd.RestApi.file.SQLMonster;

import java.util.*;

public class MonsterJsonToSqlConverter {

    public MonsterJsonToSqlConverter() {
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
                {
                    String trimmed = sense.trim();
                    if (!Objects.equals(trimmed, "Passive Perception"))
                        senses.add(trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1));
                });
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
                    String typesString = meta.substring(meta.indexOf(" ") + 1); // skip the size
                    String[] typesArray = typesString.split(",");
                    Arrays.stream(typesArray).forEach(type ->
                            types.add(type.trim().substring(0, 1).toUpperCase() + type.trim().substring(1)));
                });
        return types;
    }

    public Set<String> loadSpeeds(List<MonsterJson> monsterJsons) {
        Set<String> speeds = new HashSet<>();
        monsterJsons.forEach(monsterJson -> {
            String speedString = monsterJson.getSpeed();
            if (speedString != null) {
                speedString = speedString.trim();
                speedString = speedString.replace(" ft.", "");
                speedString = speedString.replaceAll("([0-9]+)", "");
                String[] speedArray = speedString.split(",");
                Arrays.stream(speedArray).forEach(speed -> {
                    String trimmed = speed.trim();
                    if (trimmed.length() > 0) {
                        speeds.add(trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1));
                    }
                });
            }
        });
        speeds.add("Walk");
        return speeds;
    }

    public short loadArmorClass(MonsterJson monsterJson) {
        return Short.parseShort(monsterJson.getArmorClass().substring(0, monsterJson.getArmorClass().indexOf(" ")));
    }

    public String loadArmorClassDescription(MonsterJson monsterJson) {
        String armorClass = monsterJson.getArmorClass();
        armorClass = armorClass.replace("(", "");
        armorClass = armorClass.replace(")", "");
        armorClass = armorClass.substring(armorClass.indexOf(" ") + 1);
        return armorClass;
    }

    public SQLMonster convertToMonsterSQL(MonsterJson monsterJson, long id) {
        SQLMonster sqlMonster = new SQLMonster();
        sqlMonster.setId(id);
        sqlMonster.setName(monsterJson.getName().replace("'", "''").trim());
        sqlMonster.setTypes(loadMonsterType(List.of(monsterJson)));
        sqlMonster.setSize(loadMonsterSize(List.of(monsterJson)).iterator().next());
        sqlMonster.setArmorClass(loadArmorClass(monsterJson));
        sqlMonster.setArmorClassDescription(loadArmorClassDescription(monsterJson));
        sqlMonster.setHitPoints(Short.parseShort(monsterJson.getHitPoints().
                substring(0, monsterJson.getHitPoints().indexOf(" "))));
        return sqlMonster;
    }


}
