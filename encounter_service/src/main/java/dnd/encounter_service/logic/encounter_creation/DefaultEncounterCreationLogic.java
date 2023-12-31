package dnd.encounter_service.logic.encounter_creation;

import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import dnd.encounter_service.utils.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class DefaultEncounterCreationLogic implements EncounterCreationLogic {

    private final DifficultyService difficultyService;
    @Value("${game.monster.creation.max-monsters}")
    private int maxAmountOfMonstersInEncounter;


    @Override
    public int calculateEncounterDifficultyXp(List<Float> crs) {
        return (int) (crs.stream().mapToInt(difficultyService::getXp
        ).sum() * difficultyService.getMultiplier(crs.size()));
    }


    @Override
    public int calculateEncounterGainedXp(List<Float> crs) {
        return crs.stream().mapToInt(difficultyService::getXp).sum();
    }

    /**
     * This implementation ignores the Crs from the availableCrList whose xp value is smaller than 1/10 of the min_xp
     */

    @Override
    public ArrayList<ArrayList<Float>> getCrsForEncounter(int xp, int maxAmountOfMonsters,
                                                           List<Float> availableCrList, float xpTolerance) {
        validateInput(xp, maxAmountOfMonsters, availableCrList, xpTolerance);

        int maxXp = xp + (int) (xp * xpTolerance);
        int minXp = Math.max(xp - (int) (xp * xpTolerance), 1);

        if (maxAmountOfMonsters > maxAmountOfMonstersInEncounter)
            maxAmountOfMonsters = maxAmountOfMonstersInEncounter;

        int[] xpList = availableCrList.stream().mapToInt(difficultyService::getXp).toArray();
        int upperIndex;
        int lowerIndex = 0;
        try {
            upperIndex = Arrays.binarySearch(xpList, ListUtils.BinarySearchHighestValueSmallerThanX(xpList, maxXp));
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
        if (minXp / 10 > xpList[0])
            lowerIndex = Arrays.binarySearch(xpList, ListUtils.BinarySearchHighestValueSmallerThanX(xpList, minXp / 10)) + 1;
        ArrayList<ArrayList<Float>> crsLists = new ArrayList<>();


        while (upperIndex >= lowerIndex) {
            ArrayList<ArrayList<Float>> listsOfCrs = new ArrayList<>();
            ArrayList<Float> crs = new ArrayList<>();
            listsOfCrs.add(crs);
            getCrsRecursive(maxXp, minXp, 0, 0, listsOfCrs, Arrays.copyOfRange(xpList, lowerIndex,
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


    private void validateInput(int xp, int maxAmountOfMonsters, List<Float> availableCrList, float xpTolerance) {
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
     *
     * @param maxXp               the maximum xp value of the encounter
     * @param minXp               the minimum xp value of the encounter
     * @param currentXp           the current xp value of the encounter in each recursion
     * @param index               the index of a crsList that is currently being appended. Should be set to 0 at the first call.
     * @param crs                 list of lists of crs each list represents a possible combination of crs. Should be set to an empty at
     *                            the first call.
     * @param xpList              the list of xp values representing available crs. The list has to be sorted in ascending order
     * @param maxAmountOfMonsters the maximum amount of monsters in the encounter
     */
    private void getCrsRecursive(int maxXp, int minXp, int currentXp, int index,
                                 ArrayList<ArrayList<Float>> crs, int[] xpList, int maxAmountOfMonsters) {
        Double multiplier = difficultyService.getMultiplier(crs.get(index).size() + 1);

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

        crs.get(index).add(difficultyService.getCr(addedXp));
        currentXp += addedXp;

        if (currentXp * multiplier >= minXp && (maxAmountOfMonsters - 1) != 0) {
            int arrayReductionIndex;

            try {
                double nextMultiplier = difficultyService.getMultiplier(crs.get(index).size() + 1);
                arrayReductionIndex = Arrays.binarySearch(xpList,
                        ListUtils.BinarySearchHighestValueSmallerThanX
                                (xpList, (int) (maxXp / nextMultiplier - currentXp)));
            } catch (IllegalArgumentException e) {
                return;
            }

            int originalArrayIndex = index;

            while (arrayReductionIndex >= 0) {
                ArrayList<Float> newCrs = new ArrayList<>(crs.get(originalArrayIndex));
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
