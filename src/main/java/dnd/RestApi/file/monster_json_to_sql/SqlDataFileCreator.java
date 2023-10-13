package dnd.RestApi.file.monster_json_to_sql;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.file.MonsterJson;
import dnd.RestApi.file.SQLMonster;
import dnd.RestApi.utils.BooleanUtils;

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
            writeHitDice(monster);
            writeMonster(monster);
            writeIndividualMonsterTypes(monster);
            writeIndividualMonsterSpeed(monster);
            writeIndividualMonsterSkills(monster);
            writeIndividualMonsterSenses(monster);
            writeIndividualMonsterConditionImmunities(monster);
            writeIndividualMonsterDamageImmunities(monster);
            writeIndividualMonsterDamageResistances(monster);
            writeIndividualMonsterDamageVulnerabilities(monster);
            writeIndividualMonsterActions(monster);
            writeIndividualMonsterTraits(monster);
            writeIndividualMonsterReactions(monster);
            writeIndividualMonsterLegendaryActions(monster);
            writeIndividualMonsterLanguages(monster);

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
                .append(" (id, hit_dice_id, legendary_action_description, cr, passive_perception, str_saving_throw_bonus, dex_saving_throw_bonus, con_saving_throw_bonus," +
                        "int_saving_throw_bonus, wis_saving_throw_bonus, cha_saving_throw_bonus, strength, dexterity," +
                        " constitution, intelligence, wisdom, charisma , hit_points, size_id, armor_class," +
                        " armor_class_description, monster_name) VALUES (")
                .append(monster.getId())
                .append(", ")
                .append(monster.getId())
                .append(", '")
                .append(monster.getLegendaryActionsDescription())
                .append("', ")
                .append(monster.getCr())
                .append(", ")
                .append(monster.getPassivePerception())
                .append(", ")
                .append(monster.getStrengthSaveBonus())
                .append(", ")
                .append(monster.getDexteritySaveBonus())
                .append(", ")
                .append(monster.getConstitutionSaveBonus())
                .append(", ")
                .append(monster.getIntelligenceSaveBonus())
                .append(", ")
                .append(monster.getWisdomSaveBonus())
                .append(", ")
                .append(monster.getCharismaSaveBonus())
                .append(", ")
                .append(monster.getStrength())
                .append(", ")
                .append(monster.getDexterity())
                .append(", ")
                .append(monster.getConstitution())
                .append(", ")
                .append(monster.getIntelligence())
                .append(", ")
                .append(monster.getWisdom())
                .append(", ")
                .append(monster.getCharisma())
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
                .append("');\n")
                .append("\n");

        writeIntoFile(stringBuilder);
    }

    public void writeIndividualMonsterReactions(SQLMonster sqlMonster) {
        sqlMonster.getReactions().forEach(reaction -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_REACTIONS_TABLE)
                    .append(" (monster_id, description, name) VALUES (")
                    .append(sqlMonster.getId())
                    .append(", '")
                    .append(reaction.getDescription())
                    .append("', '")
                    .append(reaction.getName())
                    .append("');\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterLanguages(SQLMonster sqlMonster) {
        sqlMonster.getLanguages().forEach(language -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_LANGUAGE_TABLE)
                    .append(" (monster_id, language_id) VALUES (")
                    .append(sqlMonster.getId())
                    .append(", ")
                    .append(languagesIdMap.get(language))
                    .append(");\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterLegendaryActions(SQLMonster sqlMonster) {
        sqlMonster.getLegendaryActions().forEach(legendaryAction -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.LEGENDARY_ACTIONS_TABLE)
                    .append(" (monster_id, description, name) VALUES (")
                    .append(sqlMonster.getId())
                    .append(", '")
                    .append(legendaryAction.getDescription())
                    .append("', '")
                    .append(legendaryAction.getName())
                    .append("');\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterSenses(SQLMonster monster) {
        monster.getSenses().forEach((sense, value) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_SENSES_TABLE)
                    .append(" (range, monster_id, sense_id) VALUES (")
                    .append(value)
                    .append(", ")
                    .append(monster.getId())
                    .append(", '")
                    .append(sensesIdMap.get(sense))
                    .append("');\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterSkills(SQLMonster monster) {
        monster.getSkills().forEach((skill, value) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTERS_SKILLS_TABLE)
                    .append(" (bonus, monster_id, skill_id) VALUES (")
                    .append(value)
                    .append(", ")
                    .append(monster.getId())
                    .append(", '")
                    .append(skillsIdMap.get(skill))
                    .append("');\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterActions(SQLMonster sqlMonster) {
        sqlMonster.getActions().forEach(action -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_ACTIONS_TABLE)
                    .append(" (name, description, monster_id) VALUES ('")
                    .append(action.getName())
                    .append("', '")
                    .append(action.getDescription())
                    .append("', ")
                    .append(sqlMonster.getId())
                    .append(");\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterTraits(SQLMonster sqlMonster) {
        sqlMonster.getTraits().forEach(trait -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_TRAITS_TABLE)
                    .append(" (title, description, monster_id) VALUES ('")
                    .append(trait.getTitle())
                    .append("', '")
                    .append(trait.getDescription())
                    .append("', ")
                    .append(sqlMonster.getId())
                    .append(");\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeIndividualMonsterSpeed(SQLMonster monster) {
        monster.getSpeeds().forEach((speed, value) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.SPEED_OF_MONSTERS_TABLE)
                    .append(" (value, monster_id, speed_id) VALUES (")
                    .append(value)
                    .append(", ")
                    .append(monster.getId())
                    .append(", '")
                    .append(speedsIdMap.get(speed))
                    .append("');\n");
            writeIntoFile(stringBuilder);
        });
    }

    public void writeHitDice(SQLMonster monster) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("INSERT INTO ")
                .append(SQLConfig.SCHEMA)
                .append(".")
                .append(SQLConfig.DICE_TABLE)
                .append(" (id, dice, amount, constant) VALUES (")
                .append(monster.getId())
                .append(", ")
                .append(monster.getHitDice().getDice())
                .append(", ")
                .append(monster.getHitDice().getAmount())
                .append(", ")
                .append(monster.getHitDice().getConstant())
                .append(");\n");
        writeIntoFile(stringBuilder);
    }

    public void writeIndividualMonsterDamageVulnerabilities(SQLMonster monster) {

        monster.getDamageVulnerabilities().forEach(damageVulnerability -> writeIndividualMonsterDamage(damageVulnerability, false,
                false, true, monster));

    }

    public void writeIndividualMonsterDamageResistances(SQLMonster monster) {

        monster.getDamageResistances().forEach(damageResistance -> writeIndividualMonsterDamage(damageResistance, true,
                false, false, monster));

    }

    private void writeIndividualMonsterDamage(String damage, boolean isResistance, boolean isImmunity,
                                              boolean isVulnerability, SQLMonster sqlMonster) {

        if (BooleanUtils.isMoreThanXTrue(1, isResistance, isImmunity, isVulnerability)) {
            throw new IllegalArgumentException("More than one of the following is true: isResistance, isImmunity," +
                    " isVulnerability");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                .append(".")
                .append(SQLConfig.MONSTER_DAMAGE_TABLE)
                .append(" (monster_id, damage_id, is_immune, is_resistant, is_vulnerable," +
                        " adamantine_attack_flag, magic_attack_flag, silver_attack_flag) VALUES (")
                .append(sqlMonster.getId())
                .append(", '")
                .append(damageTypesIdMap.get(damage))
                .append("', ")
                .append(isImmunity)
                .append(", ")
                .append(isResistance)
                .append(", ")
                .append(isVulnerability)
                .append(", ");
        if (isResistance) {
            stringBuilder.append(sqlMonster.isNotResistancesFromAdamantineAttackFlag())
                    .append(", ")
                    .append(sqlMonster.isNotResistancesFromNonMagicalAttackFlag())
                    .append(", ")
                    .append(sqlMonster.isNotResistancesFromSilverAttackFlag())
                    .append(");\n");
        } else if (isImmunity) {
            stringBuilder.append(sqlMonster.isNotImmunitiesFromAdamantineAttackFlag())
                    .append(", ")
                    .append(sqlMonster.isNotImmunitiesFromNonMagicalAttackFlag())
                    .append(", ")
                    .append(sqlMonster.isNotImmunitiesFromSilverAttackFlag())
                    .append(");\n");
        } else {
            stringBuilder.append("false, false, false);\n");
        }
        writeIntoFile(stringBuilder);
    }

    public void writeIndividualMonsterDamageImmunities(SQLMonster monster) {
        monster.getDamageImmunities().forEach(damageImmunity -> writeIndividualMonsterDamage(damageImmunity, false,
                true, false, monster));
    }

    public void writeIndividualMonsterConditionImmunities(SQLMonster monster) {
        StringBuilder stringBuilder = new StringBuilder();
        monster.getConditionImmunities().forEach(conditionImmunity -> {
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_CONDITION_IMMUNITY_TABLE)
                    .append(" (monster_id, condition_id) VALUES (")
                    .append(monster.getId())
                    .append(", '")
                    .append(conditionsIdMap.get(conditionImmunity))
                    .append("');\n");
        });
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
