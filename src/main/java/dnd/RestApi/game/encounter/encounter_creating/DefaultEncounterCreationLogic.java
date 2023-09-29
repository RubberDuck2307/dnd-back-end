package dnd.RestApi.game.encounter.encounter_creating;

import dnd.RestApi.exception.custom_exception.NoSuchEncounterException;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.creature.monster.MonsterNativeQueries;
import dnd.RestApi.game.creature.monster.MonsterRepository;
import dnd.RestApi.game.encounter.Encounter;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
import dnd.RestApi.utils.ListUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

@RequiredArgsConstructor
public class DefaultEncounterCreationLogic implements EncounterCreationLogic {

    private final EncounterDifficultyMap encounterDifficultyMap;
    private final MonsterRepository monsterRepository;
    @Value("${game.monster.creation.max-monsters}")
    private int maxAmountOfMonstersInEncounter;
    @Value("${game.monster.creation.diversity}")
    private int variabilityModifier;
    private final MonsterNativeQueries monsterNativeQueries;


    @Override
    public int calculateEncounterDifficultyXp(Encounter encounter) {
        return (int) (encounter.getMonsters().stream().mapToInt(monster -> encounterDifficultyMap.getXp(monster.getCr())
        ).sum() * encounterDifficultyMap.getMultiplier(encounter.getMonsters().size()));
    }

    @Override
    public int calculateEncounterGainedXp(Encounter encounter) {
        return encounter.getMonsters().stream().mapToInt(monster -> encounterDifficultyMap.getXp(monster.getCr())).sum();
    }


    @Override
    public ArrayList<Encounter> createRandomEncounter(Integer xp, Integer amountOfEncounters, float xpTolerance,
                                                      boolean differentKindOfMonsters, int maxAmountOfMonsters) {
        if (maxAmountOfMonsters == 1) {
            return getRandomMonstersByCR(encounterDifficultyMap.getCr(xp), amountOfEncounters).stream().map(monster -> {
                ArrayList<Monster> monsters = new ArrayList<>();
                monsters.add(monster);
                return new Encounter(this, monsters);
            }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }

        if (differentKindOfMonsters) {
            List<Double> avCrs = new ArrayList<>();
            avCrs.add(1.0);
            avCrs.add(2.0);

            ArrayList<ArrayList<Double>> allPossibleCrs = getCrsForEncounter(xp, maxAmountOfMonsters,
                    avCrs, xpTolerance);

            if (allPossibleCrs.size() == 0) {
                throw new NoSuchEncounterException("No encounter with given parameters exists");
            }
            //Randomly choose crs for each encounter
            ArrayList<ArrayList<Double>> chosenCrs = new ArrayList<>();
            for (int i = 0; i < amountOfEncounters; i++) {
                chosenCrs.add(ListUtils.getRandomElement(allPossibleCrs));
            }

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

            HashMap<Double, List<Monster>> monstersByCr = monsterNativeQueries.getMonstersByCrAndAmount(amountOfCrs);

            ArrayList<Encounter> encounters = new ArrayList<>();

            for (ArrayList<Double> crs : chosenCrs) {
                ArrayList<Monster> monsters = new ArrayList<>();
                for (Double cr : crs) {
                    monsters.add(ListUtils.getRandomElement(monstersByCr.get(cr)));
                }
                encounters.add(new Encounter(this, monsters));
            }

            return encounters;

        } else {

        }

        return null;
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

    /**
     * This implementation ignores the Crs from the availableCrList whose xp value is smaller than 1/10 of the min_xp
     */

    @Override
    public ArrayList<ArrayList<Double>> getCrsForEncounter(int xp, int maxAmountOfMonsters,
                                                           List<Double> availableCrList, float xpTolerance) {
        validateInput(xp, maxAmountOfMonsters, availableCrList, xpTolerance);

        int maxXp = xp + (int) (xp * xpTolerance);
        int minXp = Math.max(xp - (int) (xp * xpTolerance), 1);

        if (maxAmountOfMonsters > maxAmountOfMonstersInEncounter)
            maxAmountOfMonsters = maxAmountOfMonstersInEncounter;

        int[] xpList = availableCrList.stream().mapToInt(encounterDifficultyMap::getXp).toArray();

        int upperIndex = Arrays.binarySearch(xpList, ListUtils.BinarySearchHighestValueSmallerThanX(xpList, maxXp));
        //@TODO: FIX LOWER INDEX to ignore crs that are too small to prevent too many irrelevant possible combinations
        //int lowerIndex = Arrays.binarySearch(xpList, ListUtils.BinarySearchHighestValueSmallerThanX(xpList, minXp / 10)) + 1;

        ArrayList<ArrayList<Double>> crsLists = new ArrayList<>();


        while (upperIndex >= 0) {
            ArrayList<ArrayList<Double>> listsOfCrs = new ArrayList<>();
            ArrayList<Double> crs = new ArrayList<>();
            listsOfCrs.add(crs);
            getCrsRecursive(maxXp, minXp, 0, 0, listsOfCrs, Arrays.copyOfRange(xpList, 0,
                    upperIndex + 1), maxAmountOfMonsters);
            upperIndex--;
            listsOfCrs.forEach(list -> {
                if (list.size() != 0) {
                    crsLists.add(list);
                }
            });
        }

        return crsLists;
    }

    private void validateInput(int xp, int maxAmountOfMonsters, List<Double> availableCrList, float xpTolerance) {
        if (availableCrList.size() == 0) {
            throw new IllegalArgumentException("availableCrList must not be empty");
        }
        if (xp < 0) {
            throw new IllegalArgumentException("xp must be positive");
        }
        if (maxAmountOfMonsters < 1) {
            throw new IllegalArgumentException("maxAmountOfMonsters must be at least 1");
        }
        if (xpTolerance < 0) {
            throw new IllegalArgumentException("xpTolerance must be positive");
        }
    }

    /**
     * Recursive method to get all possible combinations of crs for an encounter
     * @param maxXp the maximum xp value of the encounter
     * @param minXp the minimum xp value of the encounter
     * @param currentXp the current xp value of the encounter in each recursion
     * @param index the index of a crsList that is currently being appended. Should be set to 0 at the first call.
     * @param crs list of lists of crs each list represents a possible combination of crs. Should be set to an empty at
     *            the first call.
     * @param xpList the list of xp values representing available crs. The list has to be sorted in ascending order
     * @param maxAmountOfMonsters the maximum amount of monsters in the encounter
     */
    private void getCrsRecursive(int maxXp, int minXp, int currentXp, int index,
                                 ArrayList<ArrayList<Double>> crs, int[] xpList, int maxAmountOfMonsters) {

        double multiplier = encounterDifficultyMap.getMultiplier(crs.get(index).size() + 1);

        if (maxAmountOfMonsters == 0) {
            if (currentXp * multiplier < minXp) {
                crs.get(index).clear();
            }
            return;
        }

        int addedXp = 0;
        try {
            addedXp = ListUtils.BinarySearchHighestValueSmallerThanX(
                    xpList, (int) (maxXp / multiplier - currentXp));

        } catch (IllegalArgumentException e) {  // if there is no value smaller than x
            crs.get(index).clear();
            return;
        }

        crs.get(index).add(encounterDifficultyMap.getCr(addedXp));
        currentXp += addedXp;

        if (currentXp * multiplier >= minXp && (maxAmountOfMonsters - 1) != 0) {

            int arrayReductionIndex;

            try {
                double nextMultiplier = encounterDifficultyMap.getMultiplier(crs.get(index).size() + 1);
                arrayReductionIndex = Arrays.binarySearch(xpList,
                        ListUtils.BinarySearchHighestValueSmallerThanX
                                (xpList, (int) (maxXp / nextMultiplier - currentXp)));
            } catch (IllegalArgumentException e) {
                return;
            }

            int originalArrayIndex = index;

            while (arrayReductionIndex >= 0) {

                ArrayList<Double> newCrs = new ArrayList<>(crs.get(originalArrayIndex));
                crs.add(newCrs);
                index = crs.size() - 1;

                getCrsRecursive(maxXp, minXp, currentXp, index, crs, Arrays.copyOfRange(xpList, 0,
                        arrayReductionIndex + 1), maxAmountOfMonsters - 1);

                arrayReductionIndex--;
            }


        } else
            getCrsRecursive(maxXp, minXp, currentXp, index, crs, xpList, maxAmountOfMonsters - 1);
    }
}
