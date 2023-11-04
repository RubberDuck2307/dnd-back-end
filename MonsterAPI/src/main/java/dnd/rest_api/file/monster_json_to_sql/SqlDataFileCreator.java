package dnd.rest_api.file.monster_json_to_sql;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.file.MonsterJson;
import dnd.rest_api.file.SQLMonster;
import dnd.rest_api.persistance.model.creature.Ability;

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
    private final Map<String, Long> abilityIdMap;
    private final Map<String, Integer> attackTypesIdMap;
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
        abilityIdMap = new HashMap<>();
        attackTypesIdMap = new HashMap<>();
        this.converter = converter;
    }

    public void writeAll(List<MonsterJson> monsterJsons, Map<String, String> conditions) {
        writeAbilities();
        writeSkills();
        writeDamageTypes();
        writeCondition(conditions);
        writeAttackTypes();
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
            writeIndividualMonsterAbilityScore(monster);
            writeIndividualMonsterSavingThrow(monster);
            writeIndividualMonsterTypes(monster);
            writeIndividualMonsterSpeed(monster);
            writeIndividualMonsterSkills(monster);
            writeIndividualMonsterSenses(monster);
            writeIndividualMonsterConditionImmunities(monster);
            writeIndividualMonsterDamageResistances(monster);
            writeIndividualMonsterDamageVulnerabilities(monster);
            writeIndividualMonsterActions(monster);
            writeIndividualMonsterTraits(monster);
            writeIndividualMonsterReactions(monster);
            writeIndividualMonsterLegendaryActions(monster);
            writeIndividualMonsterLanguages(monster);
            writeIntoFile(new StringBuilder("\n\n"));
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
                .append(" (id, hit_dice_id, legendary_action_description, cr, passive_perception," +
                        "hit_points, size_id, armor_class, armor_class_description, monster_name, image_url) VALUES (")
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
                .append(monster.getHitPoints())
                .append(", ")
                .append(sizesIdMap.get(monster.getSize()))
                .append(", ")
                .append(monster.getArmorClass())
                .append(", '")
                .append(monster.getArmorClassDescription())
                .append("', '")
                .append(monster.getName())
                .append("', '")
                .append(monster.getImgUrl())
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

    public void writeIndividualMonsterSavingThrow(SQLMonster sqlMonster) {
        if (sqlMonster.getStrengthSaveBonus() != null)
            writeSavingThrow("Strength", sqlMonster.getStrengthSaveBonus(), sqlMonster.getId());
        if (sqlMonster.getDexteritySaveBonus() != null)
            writeSavingThrow("Dexterity", sqlMonster.getDexteritySaveBonus(), sqlMonster.getId());
        if (sqlMonster.getConstitutionSaveBonus() != null)
            writeSavingThrow("Constitution", sqlMonster.getConstitutionSaveBonus(), sqlMonster.getId());
        if (sqlMonster.getIntelligenceSaveBonus() != null)
            writeSavingThrow("Intelligence", sqlMonster.getIntelligenceSaveBonus(), sqlMonster.getId());
        if (sqlMonster.getWisdomSaveBonus() != null)
            writeSavingThrow("Wisdom", sqlMonster.getWisdomSaveBonus(), sqlMonster.getId());
        if (sqlMonster.getCharismaSaveBonus() != null)
            writeSavingThrow("Charisma", sqlMonster.getCharismaSaveBonus(), sqlMonster.getId());
        writeIntoFile(new StringBuilder("\n"));
    }

    private void writeSavingThrow(String abilityName, int value, long monsterId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ")
                .append(SQLConfig.SCHEMA)
                .append(".")
                .append(SQLConfig.MONSTER_SAVING_THROWS_TABLE)
                .append(" (monster_id, ability_id, value) VALUES (")
                .append(monsterId)
                .append(", ")
                .append(abilityIdMap.get(abilityName))
                .append(", ")
                .append(value)
                .append(");\n");
        writeIntoFile(stringBuilder);
    }

    public void writeIndividualMonsterAbilityScore(SQLMonster sqlMonster) {
        writeAbilityScore("Strength", sqlMonster.getStrength(), sqlMonster.getId());
        writeAbilityScore("Dexterity", sqlMonster.getDexterity(), sqlMonster.getId());
        writeAbilityScore("Constitution", sqlMonster.getConstitution(), sqlMonster.getId());
        writeAbilityScore("Intelligence", sqlMonster.getIntelligence(), sqlMonster.getId());
        writeAbilityScore("Wisdom", sqlMonster.getWisdom(), sqlMonster.getId());
        writeAbilityScore("Charisma", sqlMonster.getCharisma(), sqlMonster.getId());
        writeIntoFile(new StringBuilder("\n"));
    }

    private void writeAbilityScore(String abilityName, int score, long monsterId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ")
                .append(SQLConfig.SCHEMA)
                .append(".")
                .append(SQLConfig.ABILITY_SCORE_MONSTER_TABLE)
                .append(" (monster_id, ability_id, value) VALUES (")
                .append(monsterId)
                .append(", ")
                .append(abilityIdMap.get(abilityName))
                .append(", ")
                .append(score)
                .append(");\n");
        writeIntoFile(stringBuilder);
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
        StringBuilder stringBuilder = new StringBuilder();
        monster.getDamageVulnerabilities().forEach(damageVulnerability -> {
            stringBuilder.append("INSERT INTO ")
                    .append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.MONSTER_VULNERABILITIES_TABLE)
                    .append(" (monster_id, damage_type_id) VALUES (")
                    .append(monster.getId())
                    .append(", '")
                    .append(damageTypesIdMap.get(damageVulnerability))
                    .append("');\n");
        });
        writeIntoFile(stringBuilder);

    }

    public void writeIndividualMonsterDamageResistances(SQLMonster monster) {

        monster.getDamageResistances().forEach(damageResistance -> {
            if (monster.isNotResistancesFromNonMagicalAttackFlag())
                writeIndividualMonsterDamage(damageResistance,
                        false, monster, "Magical");
            if (monster.isNotImmunitiesFromSilverAttackFlag())
                writeIndividualMonsterDamage(damageResistance,
                        false, monster, "Silvered");
            if (monster.isNotResistancesFromAdamantineAttackFlag())
                writeIndividualMonsterDamage(damageResistance,
                        false, monster, "Adamantine");
            else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("INSERT INTO ")
                        .append(SQLConfig.SCHEMA)
                        .append(".")
                        .append(SQLConfig.MONSTER_DAMAGE_TABLE)
                        .append(" (monster_id, damage_id, is_immune) VALUES (")
                        .append(monster.getId())
                        .append(", '")
                        .append(damageTypesIdMap.get(damageResistance))
                        .append("', ")
                        .append("false")
                        .append(");\n");
                writeIntoFile(stringBuilder);
            }
        });

        monster.getDamageImmunities().forEach(damageImmunity -> {
            if (monster.isNotResistancesFromNonMagicalAttackFlag())
                writeIndividualMonsterDamage(damageImmunity,
                        true, monster, "Magical");
            if (monster.isNotImmunitiesFromSilverAttackFlag())
                writeIndividualMonsterDamage(damageImmunity,
                        true, monster, "Silvered");
            if (monster.isNotResistancesFromAdamantineAttackFlag())
                writeIndividualMonsterDamage(damageImmunity,
                        true, monster, "Adamantine");
            else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("INSERT INTO ")
                        .append(SQLConfig.SCHEMA)
                        .append(".")
                        .append(SQLConfig.MONSTER_DAMAGE_TABLE)
                        .append(" (monster_id, damage_id, is_immune) VALUES (")
                        .append(monster.getId())
                        .append(", '")
                        .append(damageTypesIdMap.get(damageImmunity))
                        .append("', ")
                        .append(true)
                        .append(");\n");
                writeIntoFile(stringBuilder);
            }
        });

    }

    private void writeIndividualMonsterDamage(String damage, boolean isImmunity,
                                              SQLMonster sqlMonster, String attackTypeException) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                .append(".")
                .append(SQLConfig.MONSTER_DAMAGE_TABLE)
                .append(" (monster_id, damage_id, is_immune, attack_type_exception_id) VALUES (")
                .append(sqlMonster.getId())
                .append(", '")
                .append(damageTypesIdMap.get(damage))
                .append("', ")
                .append(isImmunity)
                .append(", ").
                append(attackTypeException == null ? "NULL" : "'" + attackTypesIdMap.get(attackTypeException) + "'");
        stringBuilder.append(");\n");
        writeIntoFile(stringBuilder);
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
        String[] abilities = {"Dexterity", "Wisdom", "Intelligence", "Strength", "Charisma", "Intelligence", "Wisdom",
                "Charisma", "Intelligence", "Wisdom", "Intelligence", "Wisdom", "Charisma", "Charisma", "Intelligence",
                "Dexterity", "Dexterity", "Wisdom"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skills.length; i++) {
            skillsIdMap.put(skills[i], i + 1);
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.SKILL_TABLE)
                    .append(" (id, name, ability_id) VALUES (")
                    .append(i + 1)
                    .append(", '")
                    .append(skills[i])
                    .append("', '")
                    .append(abilityIdMap.get(abilities[i]))
                    .append("');\n");

        }
        stringBuilder.append("\n");
        writeIntoFile(stringBuilder);
    }

    //TODO unharcoding
    public void writeAbilities() {
        Ability[] abilities = {new Ability(1L, "Strength", "STR", "Strength measures bodily power, athletic training, and the extent to which you can exert raw physical force."),
                new Ability(2L, "Dexterity", "DEX", "Dexterity measures agility, reflexes, and balance."),
                new Ability(3L, "Constitution", "CON", "Constitution measures health, stamina, and vital force."),
                new Ability(4L, "Intelligence", "INT", "Intelligence measures mental acuity, accuracy of recall, and the ability to reason."),
                new Ability(5L, "Wisdom", "WIS", "Wisdom reflects how attuned you are to the world around you and represents perceptiveness and intuition."),
                new Ability(6L, "Charisma", "CHA", "Charisma measures your ability to interact effectively with others. It includes such factors as confidence and eloquence, and it can represent a charming or commanding personality.")};

        StringBuilder stringBuilder = new StringBuilder();
        for (Ability ability : abilities) {
            abilityIdMap.put(ability.getTitle(), ability.getId());
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.ABILITY_TABLE)
                    .append(" (id, title, abbreviation, description) VALUES (")
                    .append(ability.getId())
                    .append(", '")
                    .append(ability.getTitle())
                    .append("', '")
                    .append(ability.getAbbreviation())
                    .append("', '")
                    .append(ability.getDescription())
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

    public void writeAttackTypes() {
        String[] attackTypes = {"Adamantine", "Silvered", "Magical"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < attackTypes.length; i++) {
            attackTypesIdMap.put(attackTypes[i], i + 1);
            stringBuilder.append("INSERT INTO ").append(SQLConfig.SCHEMA)
                    .append(".")
                    .append(SQLConfig.ATTACK_TYPE_TABLE)
                    .append(" (id, name) VALUES (")
                    .append(i + 1)
                    .append(", '")
                    .append(attackTypes[i])
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
