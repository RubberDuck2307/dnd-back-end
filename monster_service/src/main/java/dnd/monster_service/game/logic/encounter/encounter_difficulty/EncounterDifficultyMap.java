package dnd.monster_service.game.logic.encounter.encounter_difficulty;

import java.util.List;

public interface EncounterDifficultyMap {

    Integer getXpThreshold(Integer level, EncounterDifficultyName difficultyName);

    Double getMultiplier(Integer number);

    Integer getXp(Double cr);

    /**
        Returns cr closest to xp
     */
    Double getCr(Integer xp);

    /**
     * Returns list of xp representing each level of challenge rating (cr)
     */
    List<Integer> getXps();

    /**
     * Returns lists of alle challenge ratings
     */
    List<Double> getCRs();
}
