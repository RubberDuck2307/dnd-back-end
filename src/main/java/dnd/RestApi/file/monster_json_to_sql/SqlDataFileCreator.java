package dnd.RestApi.file.monster_json_to_sql;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.file.MonsterJson;
import dnd.RestApi.file.SQLMonster;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlDataFileCreator {
    public static final String MONSTERS_FILE_PATH = "src/main/resources/data.sql";
    private final FileWriter fileWriter;
    private final Map<String, Integer> sizesIdMap;
    private final Map<String, Integer> typesIdMap;
    private final Map<String, Integer> sensesIdMap;
    private final Map<String, Integer> languagesIdMap;
    private final Map<String, Integer> skillsIdMap;
    private final Map<String, Integer> speedsIdMap;
    private final Map<String, Integer> damageTypesIdMap;
    private final Map<String, Integer> conditionsIdMap;
    private final MonsterJsonToSqlConverter converter;
    private final PrintWriter printWriter;

    public SqlDataFileCreator(MonsterJsonToSqlConverter converter) throws IOException {
        this.fileWriter = new FileWriter(MONSTERS_FILE_PATH, true);
        printWriter = new PrintWriter(fileWriter);
        sizesIdMap = new HashMap<>();
        typesIdMap = new HashMap<>();
        sensesIdMap = new HashMap<>();
        languagesIdMap = new HashMap<>();
        skillsIdMap = new HashMap<>();
        speedsIdMap = new HashMap<>();
        damageTypesIdMap = new HashMap<>();
        conditionsIdMap = new HashMap<>();
        this.converter = converter;
    }

    public void writeAll(List<MonsterJson> monsterJsons, Map<String, String> conditions) {
        writeSkills();
        writeDamageTypes();
        writeCondition(conditions);
        writeSpeeds(converter.loadSpeeds(monsterJsons));
        writeMonsterTypes(converter.loadMonsterType(monsterJsons));
        writeCreatureSizes(converter.loadMonsterSize(monsterJsons));
        writeLanguages(converter.loadLanguages(monsterJsons));
        writeSenses(converter.loadSenses(monsterJsons));
        AtomicInteger index = new AtomicInteger(1);
        monsterJsons.forEach(monsterJson -> {
            SQLMonster monster = converter.convertToMonsterSQL(monsterJson, index.getAndIncrement());
            writeMonster(monster);
            writeIndividualMonsterTypes(monster);

        });
        printWriter.close();

    }

    public void writeMonster(SQLMonster monster) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("INSERT INTO ")
                .append(SQLConfig.SCHEMA)
                .append(".")
                .append(SQLConfig.MONSTER_TABLE)
                .append(" (id, hit_points, size_id, armor_class, armor_class_description, monster_name) VALUES (")
                .append(monster.getId())
                .append(", ")
                .append(monster.getHitPoints())
                .append(", ")
                .append(sizesIdMap.get(monster.getSize()))
                .append(", ")
                .append(monster.getArmorClass())
                .append(", '")
                .append(monster.getArmorClassDescription())
                .append("', '")
                .append(monster.getName())
                .append("');\n");
        writeIntoFile(stringBuilder);
    }

    public void writeIndividualMonsterTypes(SQLMonster monster) {
        StringBuilder stringBuilder = new StringBuilder();
        monster.getTypes().forEach(type -> {

            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_TYPE_RELATION_TABLE)
                    .append(" (monster_id, type_id) VALUES (")
                    .append(monster.getId())
                    .append(", '")
                    .append(typesIdMap.get(type))
                    .append("');\n");
        });
        writeIntoFile(stringBuilder);
    }

    public void writeCreatureSizes(Set<String> sizes) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        sizes.forEach(size -> {
            sizesIdMap.put(size, index.get());
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.CREATURE_SIZE_TABLE)
                    .append(" (id, size) VALUES (")
                    .append(index.getAndIncrement())
                    .append(", '")
                    .append(size)
                    .append("');\n");
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeMonsterTypes(Set<String> types) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        types.forEach(type -> {
            typesIdMap.put(type, index.get());
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_TYPE_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(index.getAndIncrement())
                    .append(", '")
                    .append(type)
                    .append("');\n");
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeSenses(Set<String> senses) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        senses.forEach(sense -> {
            sensesIdMap.put(sense, index.get());
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.SENSE_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(index.getAndIncrement())
                    .append(", '")
                    .append(sense)
                    .append("');\n");
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeLanguages(Set<String> languages) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        languages.forEach(language -> {
            languagesIdMap.put(language, index.get());
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.LANGUAGE_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(index.getAndIncrement())
                    .append(", '")
                    .append(language)
                    .append("');\n");
        });

        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    //@TODO: unhardcode the skills
    public void writeSkills() {
        String[] skills = {"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception", "History", "Insight",
                "Intimidation", "Investigation", "Medicine", "Nature", "Perception", "Performance", "Persuasion",
                "Religion", "Sleight of Hand", "Stealth", "Survival"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skills.length; i++) {
            skillsIdMap.put(skills[i], i + 1);
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.SKILL_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(i + 1)
                    .append(", '")
                    .append(skills[i])
                    .append("');\n");

        }
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeSpeeds(Set<String> speeds) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        speeds.forEach(speed -> {
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.SPEED_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(index.get())
                    .append(", '")
                    .append(speed)
                    .append("');\n");
            speedsIdMap.put(speed, index.get());
            index.getAndIncrement();
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeDamageTypes() {
        String[] damageTypes = {"Acid", "Bludgeoning", "Cold", "Fire", "Force", "Lightning", "Necrotic", "Piercing",
                "Poison", "Psychic", "Radiant", "Slashing", "Thunder"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < damageTypes.length; i++) {
            damageTypesIdMap.put(damageTypes[i], i + 1);
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.DAMAGE_TYPE_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(i + 1)
                    .append(", '")
                    .append(damageTypes[i])
                    .append("');\n");
        }
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    public void writeCondition(Map<String, String> condition) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        condition.forEach((name, description) -> {
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.CONDITION_TABLE)
                    .append(" (id, name, description) VALUES (")
                    .append(index.get())
                    .append(", '")
                    .append(name)
                    .append("', '")
                    .append(description)
                    .append("');\n");
            conditionsIdMap.put(name, index.get());
            index.getAndIncrement();
        });
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }


    private void writeIntoFile(StringBuilder stringBuilder) {
        printWriter.write(stringBuilder.toString());

    }


}
