package dnd.rest_api.persistance.service.implementation;

import dnd.rest_api.api.exception_handling.custom_exception.NoSuchEncounterException;
import dnd.rest_api.persistance.model.encounter.EncounterFactory;
import dnd.rest_api.persistance.repository.monster.MonsterRepository;
import dnd.rest_api.persistance.model.encounter.Encounter;
import dnd.rest_api.game.logic.encounter.encounter_creating.EncounterCreationLogic;
import dnd.rest_api.game.logic.encounter.encounter_difficulty.EncounterDifficultyMap;
import dnd.rest_api.persistance.model.creature.monster.Monster;
import dnd.rest_api.persistance.service.interfaces.EncounterService;
import dnd.rest_api.utils.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostgresEncounterService implements EncounterService {

    private final EncounterCreationLogic encounterCreationLogic;
    private final MonsterRepository monsterRepository;
    private final EncounterDifficultyMap encounterDifficultyMap;
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
     * @throws NoSuchEncounterException if there is no possible combination to create encounters with the given xp
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
        ArrayList<ArrayList<Double>> allPossibleCrs = new ArrayList<>();
        List<Double> avCrs = getAvailableCrsForMonsterGroup(monsterGroupId);

        for (Double cr : avCrs) { //get all possible crs combinations by passing only one cr at a time
            allPossibleCrs.addAll(encounterCreationLogic.getCrsForEncounter(xp, maxAmountOfMonsters,
                    Collections.singletonList(cr), xpTolerance));
        }

        return generateEncountersFromDatabase(amountOfEncounters, false, allPossibleCrs, monsterGroupId);
    }

    private ArrayList<Encounter> generateEncountersDifferentKindsOfMonster
            (int xp, int amountOfEncounters, float xpTolerance, int maxAmountOfMonsters,
             boolean onlyOneKindOfMonsterPerCr, Long monsterGroupId) {

        List<Double> avCrs = getAvailableCrsForMonsterGroup(monsterGroupId);
        ArrayList<ArrayList<Double>> allPossibleCrs = encounterCreationLogic.getCrsForEncounter(xp,
                maxAmountOfMonsters, avCrs, xpTolerance);

        if (onlyOneKindOfMonsterPerCr)
            return generateEncountersFromDatabase(amountOfEncounters, false, allPossibleCrs,
                    monsterGroupId);
        else return generateEncountersFromDatabase(amountOfEncounters, true, allPossibleCrs,
                monsterGroupId);
    }

    private List<Double> getAvailableCrsForMonsterGroup(Long monsterGroupId) {
        List<Double> avCrs;
        if (monsterGroupId == null)
            avCrs = encounterDifficultyMap.getCRs();
        else avCrs = monsterRepository.getAllCrWhereMonsterGroupsContaining_Id(monsterGroupId);
        return avCrs;
    }


    private boolean validateInputForEncounterGeneration(int xp, int amountOfEncounters, float xpTolerance, int
            maxAmountOfMonster) {
        if (xp < 0 || amountOfEncounters < 0 || xpTolerance < 0 || maxAmountOfMonster < 0) {
            throw new IllegalArgumentException("Parameters cannot be negative");
        }

        return amountOfEncounters != 0 && maxAmountOfMonster != 0;
    }


    private ArrayList<Encounter> generateEncountersFromDatabase
            (Integer amountOfEncounters, boolean differentKindOfMonsters, ArrayList<ArrayList<Double>> allPossibleCrs,
             Long monsterGroupId) {

        if (allPossibleCrs.size() == 0) {
            throw new NoSuchEncounterException("No encounter with given parameters exists");
        }

        //Randomly choose crs for each encounter
        ArrayList<ArrayList<Double>> chosenCrs = new ArrayList<>();
        for (int i = 0; i < amountOfEncounters; i++) {
            chosenCrs.add(ListUtils.getRandomElement(allPossibleCrs));
        }

        HashMap<Double, Integer> amountOfCrs = calculateAmountsOfMonstersPerCr(chosenCrs);

        HashMap<Double, List<Monster>> monstersByCr = monsterRepository.getMonstersByCrAmountAndMonsterGroupId(
                amountOfCrs, monsterGroupId);

        return createEncounters(differentKindOfMonsters, chosenCrs, monstersByCr);
    }

    private ArrayList<Encounter> createEncounters
            (boolean differentKindOfMonsters, ArrayList<ArrayList<Double>> chosenCrs, HashMap<Double,
                    List<Monster>> monstersByCr) {

        ArrayList<Encounter> encounters = new ArrayList<>();
        if (differentKindOfMonsters) {
            for (ArrayList<Double> crs : chosenCrs) {
                ArrayList<Monster> monsters = new ArrayList<>();
                for (Double cr : crs) {
                    monsters.add(ListUtils.getRandomElement(monstersByCr.get(cr)));
                }
                encounters.add(encounterFactory.createEncounter(monsters));
            }
        }
        // If there should be only one kind of monster per cr, random monster is get for each cr and then added to encounter
        // as many times as it is frequent in chosen crs list
        else {
            for (ArrayList<Double> crs : chosenCrs) {
                ArrayList<Monster> monsters = new ArrayList<>();
                Set<Double> foundCrs = new HashSet<>();
                for (Double cr : crs) {
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
    private HashMap<Double, Integer> calculateAmountsOfMonstersPerCr(ArrayList<ArrayList<Double>> chosenCrs) {
        HashMap<Double, Integer> amountOfCrs = new HashMap<>();
        for (ArrayList<Double> crs : chosenCrs) {
            Set<Double> foundCrs = new HashSet<>();
            for (Double cr : crs) {
                if (!foundCrs.contains(cr)) {
                    foundCrs.add(cr);
                    amountOfCrs.put(cr, amountOfCrs.getOrDefault(cr, 0) + 1 * variabilityModifier);
                }
            }
        }
        return amountOfCrs;
    }

    private ArrayList<Encounter> generateOneMonsterEncounters(Integer xp, Integer amountOfEncounters) {
        return getRandomMonstersByCR(encounterDifficultyMap.getCr(xp), amountOfEncounters).stream().map(monster -> {
            ArrayList<Monster> monsters = new ArrayList<>();
            monsters.add(monster);
            return encounterFactory.createEncounter(monsters);
        }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private ArrayList<Monster> getRandomMonstersByCR(double cr, int amountOfMonsters) {
        long amount = monsterRepository.countAllByCrIs(cr);
        if (amount < amountOfMonsters) {
            amountOfMonsters = Math.toIntExact(amount);
        }
        int random = (int) (Math.random() * (amount - amountOfMonsters));
        Page<Monster> monsters = monsterRepository.getAllByCrIs(cr, PageRequest.of(random, amountOfMonsters));
        return new ArrayList<>(monsters.getContent());
    }

}
