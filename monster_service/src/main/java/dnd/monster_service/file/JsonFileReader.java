package dnd.monster_service.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dnd.monster_service.game.logic.encounter.encounter_difficulty.DefaultEncounterDifficultyMap;
import dnd.monster_service.game.logic.encounter.encounter_difficulty.EncounterDifficultyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class JsonFileReader {

    private final File encounterDifficultyFile;
    private final ObjectMapper objectMapper;
    private final File monstersFile;

    @Autowired
    public JsonFileReader(@Qualifier("encounterDifficultyFile") File encounterDifficultyFile, ObjectMapper objectMapper,
                          @Qualifier("monstersFile") File monstersFile) {
        this.encounterDifficultyFile = encounterDifficultyFile;
        this.objectMapper = objectMapper;
        this.monstersFile = monstersFile;
    }

    public EncounterDifficultyMap readEncounterDifficultyMap() throws FileNotFoundException, JsonProcessingException {
        String json = readFile(encounterDifficultyFile);
        return objectMapper.readValue(json, DefaultEncounterDifficultyMap.class);
    }

    public List<MonsterJson> readMonstersJson() throws FileNotFoundException, JsonProcessingException {
        String json = readFile(monstersFile);
        return objectMapper.readValue(json, objectMapper.getTypeFactory().
                constructCollectionType(List.class, MonsterJson.class));
    }


    private String readFile(File file) throws FileNotFoundException {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    //@TODO: make this a json file
    public Map<String, String> readConditions(){
        HashMap<String, String> ConditionDescription = new HashMap<>();
        ConditionDescription.put("Blinded", "A blinded creature can’t see and automatically fails any ability check that requires sight.\n" +
                "Attack rolls against the creature have advantage, and the creature’s attack rolls have disadvantage.");
        ConditionDescription.put("Charmed", "A charmed creature can’t attack the charmer or target the charmer with harmful abilities or magical effects.\n" +
                "The charmer has advantage on any ability check to interact socially with the creature.");
        ConditionDescription.put("Deafened", "A deafened creature can’t hear and automatically fails any ability check that requires hearing.");
        ConditionDescription.put("Exhaustion", "Some special abilities and environmental hazards, such as starvation and the long-­term effects of freezing or scorching temperatures, can lead to a special condition called exhaustion. Exhaustion is measured in six levels. An effect can give a creature one or more levels of exhaustion, as specified in the effect’s description.\n" +
                "Exhaustion Effects\n" +
                "Level\tEffect\n" +
                "1\tDisadvantage on ability checks\n" +
                "2\tSpeed halved\n" +
                "3\tDisadvantage on attack rolls and saving throws\n" +
                "4\tHit point maximum halved\n" +
                "5\tSpeed reduced to 0\n" +
                "6\tDeath\n" +
                "If an already exhausted creature suffers another effect that causes exhaustion, its current level of exhaustion increases by the amount specified in the effect’s description.\n" +
                "A creature suffers the effect of its current level of exhaustion as well as all lower levels. For example, a creature suffering level 2 exhaustion has its speed halved and has disadvantage on ability checks.\n" +
                "An effect that removes exhaustion reduces its level as specified in the effect’s description, with all exhaustion effects ending if a creature’s exhaustion level is reduced below 1.\n" +
                "Finishing a long rest reduces a creature’s exhaustion level by 1, provided that the creature has also ingested some food and drink.\n" +
                "Also, being raised from the dead reduces a creature’s exhaustion level by 1.");
        ConditionDescription.put("Frightened", "A frightened creature has disadvantage on ability checks and attack rolls while the source of its fear is within line of sight.\n" +
                "The creature can’t willingly move closer to the source of its fear.");
        ConditionDescription.put("Grappled", "A grappled creature’s speed becomes 0, and it can’t benefit from any bonus to its speed.\n" +
                "The condition ends if the grappler is incapacitated (see the condition).\n" +
                "The condition also ends if an effect removes the grappled creature from the reach of the grappler or grappling effect, such as when a creature is hurled away by the thunderwave spell.");
        ConditionDescription.put("Incapacitated", "An incapacitated creature can’t take actions or reactions.");
        ConditionDescription.put("Invisible", "An invisible creature is impossible to see without the aid of magic or a special sense. For the purpose of hiding, the creature is heavily obscured. The creature’s location can be detected by any noise it makes or any tracks it leaves.\n" +
                "Attack rolls against the creature have disadvantage, and the creature’s attack rolls have advantage.");
        ConditionDescription.put("Paralyzed", "A paralyzed creature is incapacitated (see the condition) and can’t move or speak.\n" +
                "The creature automatically fails Strength and Dexterity saving throws.\n" +
                "Attack rolls against the creature have advantage.\n" +
                "Any attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature.");
        ConditionDescription.put("Petrified", "A petrified creature is transformed, along with any nonmagical object it is wearing or carrying, into a solid inanimate substance (usually stone). Its weight increases by a factor of ten, and it ceases aging.\n" +
                "The creature is incapacitated (see the condition), can’t move or speak, and is unaware of its surroundings.\n" +
                "Attack rolls against the creature have advantage.\n" +
                "The creature automatically fails Strength and Dexterity saving throws.\n" +
                "The creature has resistance to all damage.\n" +
                "The creature is immune to poison and disease, although a poison or disease already in its system is suspended, not neutralized.");
        ConditionDescription.put("Poisoned", "A poisoned creature has disadvantage on attack rolls and ability checks.");
        ConditionDescription.put("Prone", "A prone creature’s only movement option is to crawl, unless it stands up and thereby ends the condition.\n" +
                "The creature has disadvantage on attack rolls.\n" +
                "An attack roll against the creature has advantage if the attacker is within 5 feet of the creature. Otherwise, the attack roll has disadvantage.");
        ConditionDescription.put("Restrained", "A restrained creature’s speed becomes 0, and it can’t benefit from any bonus to its speed.\n" +
                "Attack rolls against the creature have advantage, and the creature’s attack rolls have disadvantage.\n" +
                "The creature has disadvantage on Dexterity saving throws.");
        ConditionDescription.put("Stunned", "A stunned creature is incapacitated (see the condition), can’t move, and can speak only falteringly.\n" +
                "The creature automatically fails Strength and Dexterity saving throws.\n" +
                "Attack rolls against the creature have advantage.");
        ConditionDescription.put("Unconscious", "An unconscious creature is incapacitated (see the condition), can’t move or speak, and is unaware of its surroundings\n" +
                "The creature drops whatever it’s holding and falls prone.\n" +
                "The creature automatically fails Strength and Dexterity saving throws.\n" +
                "Attack rolls against the creature have advantage.\n" +
                "Any attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature.");
        return ConditionDescription;
    }


}
