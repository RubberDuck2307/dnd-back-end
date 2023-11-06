package dnd.encounter_service.logic.encounter_difficulty;

import java.util.List;

public interface EncounterDifficultyMap {

    int getXpThreshold(int level, EncounterDifficultyName difficultyName);

    double getMultiplier(int number);

    int getXp(float cr);

    /**
        Returns cr closest to xp
     */
    float getCr(int xp);

    /**
     * Returns list of xp representing each level of challenge rating (cr)
     */
    List<Integer> getXps();

    /**
     * Returns lists of alle challenge ratings
     */
    List<Float> getCRs();
}
