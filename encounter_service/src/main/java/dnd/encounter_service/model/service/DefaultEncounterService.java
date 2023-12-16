package dnd.encounter_service.model.service;

import dnd.encounter_service.logic.encounter_creation.EncounterCreationLogic;
import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import dnd.encounter_service.model.entity.encounter.Monster;
import dnd.encounter_service.model.entity.encounter.Encounter;
import dnd.encounter_service.model.entity.encounter.EncounterFactory;
import dnd.encounter_service.model.service.interfaces.EncounterService;
import dnd.encounter_service.model.service.interfaces.MonsterViewService;
import dnd.encounter_service.utils.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import dnd.encounter_service.exception.NoSuchEncounterException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultEncounterService implements EncounterService {

    private final EncounterCreationLogic encounterCreationLogic;
    private final MonsterViewService monsterViewService;
    private final DifficultyService difficultyService;
    private final EncounterFactory encounterFactory;
    @Value("${game.monster.creation.diversity}")
    private int variabilityModifier;


    /**
     * Creates random encounter with given xp, every encounter will have only one kind of monster per cr.
     * For Example: Encounter made of 2 monsters with cr 1 and 2 monsters with cr 2 will have 1 kind of monster with cr
     * 1 and 1 kind of monster with cr 2
     *
     * @param xp                      desired xp for encounter
     * @param amountOfEncounters      amount of encounters to create, the result can contain fewer encounters
     *                                if there are not enough monsters with given xp
     * @param xpTolerance             fraction specifying how much xp can be off from desired xp
     * @param differentKindOfMonsters if true, there will be multiple kinds of monsters in each encounter
     * @param maxAmountOfMonster      maximal amount of monsters in encounter. If set to 1, the method will return encounters
     *                                with monsters that are closest to the given xp, ignoring the xpTolerance
     * @return list of encounters
     * @throws dnd.encounter_service.exception.NoSuchEncounterException if there is no possible combination to create encounters with the given xp
     */


    @Override
    public ArrayList<Encounter> createRandomEncounter(int xp, int amountOfEncounters, float xpTolerance,
                                                      boolean differentKindOfMonsters, int maxAmountOfMonster) {
        return createRandomEncounter(xp, amountOfEncounters, xpTolerance, differentKindOfMonsters, maxAmountOfMonster,
                true, null);
    }


    /**
     * Creates random encounter with given xp
     *
     * @param xp                        desired xp for encounter
     * @param amountOfEncounters        amount of encounters to create, the result can contain fewer encounters
     *                                  if there are not enough monsters with given xp
     * @param xpTolerance               fraction specifying how much xp can be off from desired xp
     * @param differentKindOfMonsters   if true, there will be multiple kinds of monsters in each encounter
     * @param maxAmountOfMonsters       maximal amount of monsters in encounter. If set to 1, the method will return encounters
     *                                  with monsters that are closest to the given xp, ignoring the xpTolerance
     * @param onlyOneKindOfMonsterPerCr if true, there will be only one kind of monster per cr in each encounter
     *                                  For Example: If true, Encounter made of 2 monsters with cr 1 and 2 monsters with
     *                                  cr 2 will have 1 kind of monster with cr 1 and 1 kind of monster with cr 2
     *                                  If false, the encounter can have multiple kinds of monsters with the same cr
     * @return list of encounters
     * @throws NoSuchEncounterException if there is no possible combination to create encounters with the given xp
     */


    @Override
    public ArrayList<Encounter> createRandomEncounter(int xp, int amountOfEncounters, float xpTolerance,
                                                      boolean differentKindOfMonsters, int maxAmountOfMonsters,
                                                      boolean onlyOneKindOfMonsterPerCr, Long monsterGroupId) {

        if (!validateInputForEncounterGeneration(xp, amountOfEncounters, xpTolerance, maxAmountOfMonsters))
            return new ArrayList<>();

        if (maxAmountOfMonsters == 1) {
            return generateOneMonsterEncounters(xp, amountOfEncounters);
        }

        if (differentKindOfMonsters) {
            return generateEncountersDifferentKindsOfMonster(xp, amountOfEncounters, xpTolerance,
                    maxAmountOfMonsters, onlyOneKindOfMonsterPerCr, monsterGroupId);

        } else {
            return generateEncountersSameKindOfMonsters(xp, amountOfEncounters, xpTolerance, maxAmountOfMonsters,
                    monsterGroupId);

        }
    }

    private ArrayList<Encounter> generateEncountersSameKindOfMonsters
            (int xp, int amountOfEncounters, float xpTolerance, int maxAmountOfMonsters, Long monsterGroupId) {
        ArrayList<ArrayList<Float>> allPossibleCrs = new ArrayList<>();

        List<Float> avCrs = monsterGroupId == null || monsterGroupId == 0 ? difficultyService.getCRs() :
                monsterViewService.getCrsByMonsterGroup(monsterGroupId);

        for (Float cr : avCrs) { //get all possible crs combinations by passing only one cr at a time
            allPossibleCrs.addAll(encounterCreationLogic.getCrsForEncounter(xp, maxAmountOfMonsters,
                    Collections.singletonList(cr), xpTolerance));
        }

        return generateEncounters(amountOfEncounters, false, allPossibleCrs, monsterGroupId);
    }

    private ArrayList<Encounter> generateEncountersDifferentKindsOfMonster
            (int xp, int amountOfEncounters, float xpTolerance, int maxAmountOfMonsters,
             boolean onlyOneKindOfMonsterPerCr, Long monsterGroupId) {
        List<Float> avCrs;
        if (monsterGroupId == null || monsterGroupId == 0)
            avCrs = difficultyService.getCRs();
        else
            avCrs = monsterViewService.getCrsByMonsterGroup(monsterGroupId);
        ArrayList<ArrayList<Float>> allPossibleCrs = encounterCreationLogic.getCrsForEncounter(xp,
                maxAmountOfMonsters, avCrs, xpTolerance);

        if (onlyOneKindOfMonsterPerCr)
            return generateEncounters(amountOfEncounters, false, allPossibleCrs,
                    monsterGroupId);
        else return generateEncounters(amountOfEncounters, true, allPossibleCrs,
                monsterGroupId);
    }


    private boolean validateInputForEncounterGeneration(int xp, int amountOfEncounters, float xpTolerance, int
            maxAmountOfMonster) {
        if (xp < 0 || amountOfEncounters < 0 || xpTolerance < 0 || maxAmountOfMonster < 0) {
            throw new IllegalArgumentException("Parameters cannot be negative");
        }

        return amountOfEncounters != 0 && maxAmountOfMonster != 0;
    }


    private ArrayList<Encounter> generateEncounters
            (Integer amountOfEncounters, boolean differentKindOfMonsters, ArrayList<ArrayList<Float>> allPossibleCrs,
             Long monsterGroupId) {

        if (allPossibleCrs.size() == 0) {
            throw new NoSuchEncounterException("No encounter with given parameters exists");
        }

        //Randomly choose crs for each encounter
        ArrayList<ArrayList<Float>> chosenCrs = new ArrayList<>();
        for (int i = 0; i < amountOfEncounters; i++) {
            chosenCrs.add(ListUtils.getRandomElement(allPossibleCrs));
        }

        HashMap<Float, Integer> amountOfCrs = calculateAmountsOfMonstersPerCr(chosenCrs);
        Map<Float, List<Monster>> monstersByCr;
        if (monsterGroupId == null || monsterGroupId == 0) {
            monstersByCr = monsterViewService.getMonstersByCrAmount(amountOfCrs);
        } else {
            monstersByCr = monsterViewService.getMonstersByCrAndGroup(
                    amountOfCrs, monsterGroupId);
        }
        return createEncounters(differentKindOfMonsters, chosenCrs, monstersByCr);
    }

    private ArrayList<Encounter> createEncounters
            (boolean differentKindOfMonsters, ArrayList<ArrayList<Float>> chosenCrs, Map<Float,
                    List<Monster>> monstersByCr) {

        ArrayList<Encounter> encounters = new ArrayList<>();
        if (differentKindOfMonsters) {
            for (ArrayList<Float> crs : chosenCrs) {
                ArrayList<Monster> monsters = new ArrayList<>();
                for (Float cr : crs) {
                    monsters.add(ListUtils.getRandomElement(monstersByCr.get(cr)));
                }
                encounters.add(encounterFactory.createEncounter(monsters));
            }
        }
        // If there should be only one kind of monster per cr, random monster is get for each cr and then added to encounter
        // as many times as it is frequent in chosen crs list
        else {
            for (ArrayList<Float> crs : chosenCrs) {
                ArrayList<Monster> monsters = new ArrayList<>();
                Set<Float> foundCrs = new HashSet<>();
                for (Float cr : crs) {
                    if (foundCrs.contains(cr)) {
                        continue;
                    }
                    foundCrs.add(cr);
                    Monster monster = ListUtils.getRandomElement(monstersByCr.get(cr));
                    for (int i = 0; i < Collections.frequency(crs, cr); i++) {
                        monsters.add(monster);
                    }
                }
                encounters.add(encounterFactory.createEncounter(monsters));
            }
        }
        return encounters;
    }


    /**
     * Calculate amount of monsters for each cr adjusted by variabilityModifier to increase diversity
     *
     * @param chosenCrs list of crs for each encounter
     * @return Hash map with cr as key and amount of monsters with that cr as value
     */
    private HashMap<Float, Integer> calculateAmountsOfMonstersPerCr(ArrayList<ArrayList<Float>> chosenCrs) {
        HashMap<Float, Integer> amountOfCrs = new HashMap<>();
        for (ArrayList<Float> crs : chosenCrs) {
            Set<Float> foundCrs = new HashSet<>();
            for (Float cr : crs) {
                if (!foundCrs.contains(cr)) {
                    foundCrs.add(cr);
                    amountOfCrs.put(cr, amountOfCrs.getOrDefault(cr, 0) + 1 * variabilityModifier);
                }
            }
        }
        return amountOfCrs;
    }

    private ArrayList<Encounter> generateOneMonsterEncounters(Integer xp, Integer amountOfEncounters) {
        return monsterViewService.getRandomMonstersByCr(difficultyService.getCr(xp), amountOfEncounters).stream()
                .map(monster -> {
                    ArrayList<Monster> monsters = new ArrayList<>();
                    monsters.add(monster);
                    return encounterFactory.createEncounter(monsters);
                }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
