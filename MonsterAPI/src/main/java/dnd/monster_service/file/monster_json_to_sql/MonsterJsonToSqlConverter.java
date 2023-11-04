package dnd.monster_service.file.monster_json_to_sql;

import dnd.monster_service.file.MonsterJson;
import dnd.monster_service.file.SQLMonster;
import dnd.monster_service.persistance.model.creature.monster.LegendaryAction;
import dnd.monster_service.persistance.model.creature.monster.MonsterAction;
import dnd.monster_service.persistance.model.creature.monster.MonsterReaction;
import dnd.monster_service.persistance.model.creature.monster.MonsterTrait;
import dnd.monster_service.persistance.model.dice.DiceRoll;

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

    public void loadSavingThrows(MonsterJson monsterJson, SQLMonster sqlMonster) {
        if (monsterJson.getSavingThrows() == null) return;
        String[] savingThrowsArray = monsterJson.getSavingThrows().split(",");
        Arrays.stream(savingThrowsArray).forEach(savingThrow -> {
            String[] savingThrowArray = savingThrow.trim().split(" ");
            String savingThrowString = savingThrowArray[0].trim();
            switch (savingThrowString) {
                case "WIS" -> sqlMonster.setWisdomSaveBonus(Short.parseShort(savingThrowArray[1]));
                case "STR" -> sqlMonster.setStrengthSaveBonus(Short.parseShort(savingThrowArray[1]));
                case "DEX" -> sqlMonster.setDexteritySaveBonus(Short.parseShort(savingThrowArray[1]));
                case "CON" -> sqlMonster.setConstitutionSaveBonus(Short.parseShort(savingThrowArray[1]));
                case "INT" -> sqlMonster.setIntelligenceSaveBonus(Short.parseShort(savingThrowArray[1]));
                case "CHA" -> sqlMonster.setCharismaSaveBonus(Short.parseShort(savingThrowArray[1]));
            }
        });
    }

    public Set<String> loadSpeeds(List<MonsterJson> monsterJsons) {
        Set<String> speeds = new HashSet<>();
        speeds.add("Walk");
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
        sqlMonster.setHitDice(loadHitDice(monsterJson));
        sqlMonster.setSpeeds(loadSpeedValue(monsterJson));
        sqlMonster.setStrength((short) monsterJson.getSTR());
        sqlMonster.setDexterity((short) monsterJson.getDEX());
        sqlMonster.setConstitution((short) monsterJson.getCON());
        sqlMonster.setIntelligence((short) monsterJson.getINT());
        sqlMonster.setWisdom((short) monsterJson.getWIS());
        sqlMonster.setCharisma((short) monsterJson.getCHA());
        loadSavingThrows(monsterJson, sqlMonster);
        sqlMonster.setSkills(loadSkillsValue(monsterJson));
        sqlMonster.setPassivePerception(loadPassivePerception(monsterJson));
        sqlMonster.setSenses(loadSensesValues(monsterJson));
        loadDamageImmunities(monsterJson, sqlMonster);
        loadDamageResistances(monsterJson, sqlMonster);
        sqlMonster.setDamageVulnerabilities(loadDamageVulnerabilities(monsterJson));
        sqlMonster.setConditionImmunities(loadConditionImmunities(monsterJson));
        sqlMonster.setCr(loadCr(monsterJson));
        sqlMonster.setImgUrl(monsterJson.getImgUrl());
        sqlMonster.setActions(loadActions(monsterJson));
        sqlMonster.setTraits(loadTraits(monsterJson));
        sqlMonster.setReactions(loadReactions(monsterJson));
        sqlMonster.setLegendaryActions(loadLegendaryActions(monsterJson));
        sqlMonster.setLegendaryActionsDescription(loadLegendaryActionDescription(monsterJson));
        sqlMonster.setLanguages(loadLanguages(List.of(monsterJson)));

        return sqlMonster;
    }

    public Map<String, Short> loadSpeedValue(MonsterJson monsterJson) {
        Set<String> speedSet = loadSpeeds(List.of(monsterJson));
        HashMap<String, Short> speedValues = new HashMap<>();
        String[] speeds = monsterJson.getSpeed().replace("ft.", "").split(",");
        for (int i = 0; i < speeds.length; i++) {
            speeds[i] = speeds[i].replaceAll("[a-zA-Z.()]", " ");
            speeds[i] = speeds[i].trim();
            speedValues.put(speedSet.toArray(new String[0])[i],
                    Short.parseShort(speeds[i]));
        }
        return speedValues;
    }

    public DiceRoll loadHitDice(MonsterJson monsterJson) {
        String hitDice = monsterJson.getHitPoints();
        hitDice = hitDice.replace(" ", "");

        boolean isConstantMinus = false;
        boolean isConstant = true;

        int indexOfOpenBracket = hitDice.indexOf("(");
        int indexOfCloseBracket = hitDice.indexOf(")");
        int indexOfD = hitDice.indexOf("d");
        int indexOfSign = hitDice.indexOf("+");
        if (indexOfSign == -1) {
            indexOfSign = hitDice.indexOf("-");
            isConstantMinus = true;
        }
        if (indexOfSign == -1) {
            indexOfSign = indexOfCloseBracket;
            isConstant = false;
        }

        short hitDiceNumber = Short.parseShort((hitDice.substring(indexOfOpenBracket + 1, indexOfD)));
        short hitDiceType = Short.parseShort((hitDice.substring(indexOfD + 1, indexOfSign)));

        short constant = 0;
        if (isConstant) {
            constant = Short.parseShort((hitDice.substring(indexOfSign + 1, indexOfCloseBracket)));
            if (isConstantMinus) {
                constant = (short) - constant;
            }
        }

        return new DiceRoll(hitDiceType, hitDiceNumber, constant);
    }

    public Map<String, Short> loadSkillsValue(MonsterJson monsterJson) {
        Map<String, Short> skills = new HashMap<>();
        if (monsterJson.getSkills() == null) return skills;

        String[] skillsArray = monsterJson.getSkills().split(",");

        Arrays.stream(skillsArray).forEach(skill ->
                skills.put(skill.substring(0, skill.indexOf("+")).trim(),
                        Short.parseShort(skill.substring(skill.indexOf("+")).trim()))
        );

        return skills;
    }

    public short loadPassivePerception(MonsterJson monster) {
        String senses = monster.getSenses();
        if (senses == null) return 0;
        String perception = senses.substring(senses.indexOf("Passive Perception") + 19);
        return Short.parseShort(perception);
    }

    public Map<String, Short> loadSensesValues(MonsterJson monsterJson) {
        Map<String, Short> senses = new HashMap<>();
        if (monsterJson.getSenses() == null) return senses;
        Arrays.stream(monsterJson.getSenses().split(",")).forEach(sense -> {
            if (sense.contains("Passive Perception")) return;
            sense = sense.replace(" ft.", "");
            String senseValue = sense.replaceAll("[a-zA-Z.()]", "");
            String senseName = sense.replaceAll("([0-9]+)", "");
            senses.put(senseName.trim(),
                    Short.parseShort(senseValue.trim()));
        });
        return senses;
    }

    public void loadDamageImmunities(MonsterJson monsterJson, SQLMonster sqlMonster) {
        List<String> immunities = new ArrayList<>();
        if (monsterJson.getDamageImmunities() == null) {
            sqlMonster.setDamageImmunities(immunities);
            return;
        }
        String immunitiesString = monsterJson.getDamageImmunities().replace(";", ",");
        immunitiesString = immunitiesString.replace("and", "");
        Arrays.stream(immunitiesString.split(",")).forEach(immunity -> {
            immunity = setDamageFlags(immunity, sqlMonster, false, true);
            immunity = immunity.trim();
            immunities.add(immunity);
        });
        sqlMonster.setDamageImmunities(immunities);

    }

    public void loadDamageResistances(MonsterJson monsterJson, SQLMonster sqlMonster) {
        List<String> resistances = new ArrayList<>();
        if (monsterJson.getDamageResistances() == null) {
            sqlMonster.setDamageResistances(resistances);
            return;
        }
        String resistancesString = monsterJson.getDamageResistances().replace(";", ",");
        resistancesString = resistancesString.replace("and", "");

        Arrays.stream(resistancesString.split(",")).forEach(resistance -> {
            resistance = setDamageFlags(resistance, sqlMonster, true, false);

            resistance = resistance.trim();
            resistances.add(resistance);
        });
        sqlMonster.setDamageResistances(resistances);
    }

    public List<String> loadConditionImmunities(MonsterJson monsterJson) {
        List<String> immunities = new ArrayList<>();
        if (monsterJson.getConditionImmunities() == null) return immunities;
        Arrays.stream(monsterJson.getConditionImmunities().split(",")).forEach(immunity -> {
            immunity = immunity.trim();
            immunities.add(immunity);
        });
        return immunities;
    }

    public List<String> loadDamageVulnerabilities(MonsterJson monsterJson) {
        List<String> vulnerabilities = new ArrayList<>();
        if (monsterJson.getDamageVulnerabilities() == null) return vulnerabilities;
        Arrays.stream(monsterJson.getDamageVulnerabilities().split(",")).forEach(vulnerability -> {
            vulnerability = vulnerability.trim();
            vulnerabilities.add(vulnerability);
        });
        return vulnerabilities;
    }

    private String setDamageFlags(String damage, SQLMonster sqlMonster, boolean isResistance, boolean isImmunity) {
        if (damage.contains("from Nonmagical Attacks that aren't Silvered")) {
            damage = damage.replace("from Nonmagical Attacks that aren't Silvered", "");
            if (isResistance) {
                sqlMonster.setNotResistancesFromNonMagicalAttackFlag(true);
                sqlMonster.setNotResistancesFromSilverAttackFlag(true);
            }
            if (isImmunity) {
                sqlMonster.setNotImmunitiesFromNonMagicalAttackFlag(true);
                sqlMonster.setNotImmunitiesFromSilverAttackFlag(true);

            }
            return damage;
        }
        if (damage.contains("from Nonmagical Attacks that aren't Adamantine")) {
            damage = damage.replace("from Nonmagical Attacks that aren't Adamantine", "");
            if (isResistance) {
                sqlMonster.setNotResistancesFromNonMagicalAttackFlag(true);
                sqlMonster.setNotResistancesFromAdamantineAttackFlag(true);
            }
            if (isImmunity) {
                sqlMonster.setNotImmunitiesFromNonMagicalAttackFlag(true);
                sqlMonster.setNotImmunitiesFromAdamantineAttackFlag(true);
            }
            return damage;
        }
        if (damage.contains("from Nonmagical Attacks")) {
            damage = damage.replace("from Nonmagical Attacks", "");
            if (isResistance) sqlMonster.setNotResistancesFromNonMagicalAttackFlag(true);
            if (isImmunity) sqlMonster.setNotImmunitiesFromNonMagicalAttackFlag(true);
            return damage;
        }
        return damage;
    }

    public String loadCr(MonsterJson monsterJson) {
        String cr = monsterJson.getChallenge();
        cr = cr.substring(0, cr.indexOf(" "));
        if (cr.length() == 0) throw new IllegalArgumentException("CR is empty");
        cr = cr.replace("1/8", "0.125");
        cr = cr.replace("1/4", "0.25");
        cr = cr.replace("1/2", "0.5");
        return cr.trim();
    }

    public List<MonsterAction> loadActions(MonsterJson monsterJson) {
        List<MonsterAction> actions = new ArrayList<>();
        if (monsterJson.getActions() == null) return actions;
        Map<String, String> nameDescriptionActions = loadNameDescriptionActions(monsterJson.getActions());
        nameDescriptionActions.forEach((name, description) -> {
            MonsterAction action = new MonsterAction();
            action.setName(name);
            action.setDescription(description);
            actions.add(action);
        });
        return actions;
    }

    public List<MonsterTrait> loadTraits(MonsterJson monsterJson){
        List<MonsterTrait> traits = new ArrayList<>();
        if (monsterJson.getTraits() == null) return traits;
        Map<String, String> nameDescriptionActions = loadNameDescriptionActions(monsterJson.getTraits());
        nameDescriptionActions.forEach((name, description) -> {
            MonsterTrait trait = new MonsterTrait();
            trait.setTitle(name);
            trait.setDescription(description);
            traits.add(trait);
        });
        return traits;
    }

    public List<MonsterReaction> loadReactions(MonsterJson monsterJson){
        List<MonsterReaction> reactions = new ArrayList<>();
        if (monsterJson.getReactions() == null) return reactions;
        Map<String, String> nameDescriptionActions = loadNameDescriptionActions(monsterJson.getReactions());
        nameDescriptionActions.forEach((name, description) -> {
            MonsterReaction reaction = new MonsterReaction();
            reaction.setName(name);
            reaction.setDescription(description);
            reactions.add(reaction);
        });
        return reactions;
    }

    private Map<String, String> loadNameDescriptionActions(String actions) {
        Map<String, String> nameDescriptionActions = new HashMap<>();
        if (actions == null) return nameDescriptionActions;
        actions = actions.replace("<em>", "");
        actions = actions.replace("</em>", "");
        actions = actions.replace("<p>", "");
        actions = actions.replace("</p>", "");
        actions = actions.replace("<div>", "");
        actions = actions.replace("'", "''");
        String[] actionsArray = actions.split("<strong>");
        Arrays.stream(actionsArray).forEach(action -> {
            action = action.trim();
            if (action.length() == 0) return;
            String name = action.substring(0, action.indexOf("</strong>") );
            name = name.replace(".", "").trim();
            String description = action.substring(action.indexOf("</strong>") + 9).trim();
            nameDescriptionActions.put(name, description);
        });

        return nameDescriptionActions;
    }

    public List<LegendaryAction> loadLegendaryActions(MonsterJson monsterJson){
        List<LegendaryAction> legendaryActions = new ArrayList<>();
        if (monsterJson.getLegendaryActions() == null) return legendaryActions;
        String legendaryActionsString = monsterJson.getLegendaryActions().substring(monsterJson
                .getLegendaryActions().indexOf("</p>") + 4);
        Map<String, String> nameDescriptionActions = loadNameDescriptionActions(legendaryActionsString);
        nameDescriptionActions.forEach((name, description) -> {
            LegendaryAction legendaryAction = new LegendaryAction();
            legendaryAction.setName(name);
            legendaryAction.setDescription(description);
            legendaryActions.add(legendaryAction);
        });
        return legendaryActions;
    }
    public String loadLegendaryActionDescription(MonsterJson monsterJson){
        if (monsterJson.getLegendaryActions() == null) return null;
        String legendaryActionsString = monsterJson.getLegendaryActions().substring(0, monsterJson
                .getLegendaryActions().indexOf("</p>") + 4);
        legendaryActionsString = legendaryActionsString.replace("<p>", "");
        legendaryActionsString = legendaryActionsString.replace("</p>", "");
        legendaryActionsString = legendaryActionsString.replace("'", "''");
        legendaryActionsString = legendaryActionsString.trim();
        return legendaryActionsString;
    }


}



