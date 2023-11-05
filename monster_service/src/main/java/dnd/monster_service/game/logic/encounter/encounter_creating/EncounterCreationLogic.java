package dnd.monster_service.game.logic.encounter.encounter_creating;

import java.util.ArrayList;
import java.util.List;

public interface EncounterCreationLogic {

    int calculateEncounterDifficultyXp(List<Float> crs);

    int calculateEncounterGainedXp(List<Float> crs);

    /**
     * Calculates all possible combinations of crs that add up to xp
     * @param xp desired xp for encounter
     * @param maxAmountOfMonsters maximal amount of monsters in encounter
     * @param availableCrList list of available crs for the encounters
     * @param xpTolerance fraction specifying how much xp can be off from desired xp.
     *                    Example: 0.1 means that xp can be 10% off from desired xp
     * @return list of lists of crs that add up to xp each list represents one possible encounter
     */
    ArrayList<ArrayList<Double>> getCrsForEncounter(int xp, int maxAmountOfMonsters, List<Float> availableCrList,
                                                     float xpTolerance);

}
