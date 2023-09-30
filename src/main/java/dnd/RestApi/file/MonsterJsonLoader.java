package dnd.RestApi.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.creature.monster.MonsterService;
import dnd.RestApi.game.creature.type.MonsterType;
import dnd.RestApi.game.creature.type.TypeService;
import dnd.RestApi.game.dice.DiceRoll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.sql.Types;
import java.util.*;

/**
 * This class is responsible for loading monsters from the json file to database.
 */

@Component
@RequiredArgsConstructor
public class MonsterJsonLoader {

    private final JsonFileReader fileReader;
    private final TypeService typeService;
    private final MonsterService monsterService;

    public void loadMonsters() throws FileNotFoundException, JsonProcessingException {
        List<MonsterJson> monsterJsons = fileReader.readMonstersJson();
        List<Monster> monstersToSave = monsterJsons.stream().map(this::convertMonster).toList();
        monsterService.saveMultiple(monstersToSave);
    }

    private Monster convertMonster(MonsterJson monster) {
        Monster monsterToSave = new Monster();

        monsterToSave.setMonsterName(monster.getName());
        SetType(monster, monsterToSave);
        setHitDice(monster, monsterToSave);

        return monsterToSave;
    }

    private void SetType(MonsterJson monsterJson, Monster monsterToSave) {
        Set<MonsterType> types = new HashSet<>();
        Arrays.stream(monsterJson.getMeta().split(",")).toList().forEach(type -> {
            type = type.substring(0, 1).toUpperCase() + type.substring(1);
            type = type.strip();
            if (typeService.existsByName(type)) {
                types.add(typeService.getTypeByName(type));
            } else {
                MonsterType monsterType = typeService.save(new MonsterType(type));
                types.add(monsterType);
            }
        });
        monsterToSave.setTypes(types);
    }

    private void setHitDice(MonsterJson monsterJson, Monster monsterToSave) {
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

        int hitDiceNumber = Integer.parseInt(hitDice.substring(indexOfOpenBracket + 1, indexOfD));
        int hitDiceType = Integer.parseInt(hitDice.substring(indexOfD + 1, indexOfSign));

        int constant = 0;
        if (isConstant) {
             constant = Integer.parseInt(hitDice.substring(indexOfSign + 1, indexOfCloseBracket));
             if (isConstantMinus) {
                 constant = -constant;
             }
        } ;


        DiceRoll diceRoll = new DiceRoll(hitDiceType, hitDiceNumber, constant);
        monsterToSave.setHitDice(diceRoll);
    }

}
