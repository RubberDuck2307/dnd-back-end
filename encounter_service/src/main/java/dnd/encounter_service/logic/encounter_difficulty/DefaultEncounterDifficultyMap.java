package dnd.encounter_service.logic.encounter_difficulty;

import dnd.encounter_service.utils.ListUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Setter
@Getter
public class DefaultEncounterDifficultyMap implements EncounterDifficultyMap {

    private ArrayList<EncounterDifficultySetting> thresholds;
    private ArrayList<DifficultyMultiplierSetting> multipliers;
    private ArrayList<ChallengeRatingSetting> challengeRatings;

    @Override
    public int getXpThreshold(int level, EncounterDifficultyName difficultyName) {
        return thresholds.stream().filter(threshold -> threshold.getLevel().equals(level)).
                findFirst().orElseThrow().getDifficulty(difficultyName);
    }

    @Override
    public double getMultiplier(int number) {
        int[] array = multipliers.stream().mapToInt(DifficultyMultiplierSetting::getNumber).toArray();
        int correspondingMultiplier = ListUtils.BinarySearchHighestValueSmallerThanX(array, number);
        Double multiplier  = 1.0;
        for (DifficultyMultiplierSetting difficultyMultiplierSetting : multipliers) {
            if (difficultyMultiplierSetting.getNumber() == correspondingMultiplier) {
                multiplier = difficultyMultiplierSetting.getMultiplier();
                break;
            }
        }
        return multiplier;
    }

    @Override
    public int getXp(float cr) {
        return challengeRatings.stream().filter(challengeRatingSetting -> challengeRatingSetting.getCr() == cr).
                findFirst().orElseThrow(() -> new NoSuchElementException("Invalid cr value")).getXp();
    }

    @Override
    public float getCr(int xp) {
        ChallengeRatingSetting closestChallengeRatingSetting = challengeRatings.get(0);
        for (ChallengeRatingSetting challengeRatingSetting : challengeRatings) {
            {
                if (Math.abs(challengeRatingSetting.getXp() - xp) < Math.abs(closestChallengeRatingSetting.getXp() - xp)) {
                    closestChallengeRatingSetting = challengeRatingSetting;
                }
            }
        }
        return closestChallengeRatingSetting.getCr();
    }

    @Override
    public ArrayList<Integer> getXps() {
        return new ArrayList<>(challengeRatings.stream().map(ChallengeRatingSetting::getXp).toList());
    }

    @Override
    public List<Float> getCRs() {
        return challengeRatings.stream().map(ChallengeRatingSetting::getCr).toList();
    }

}

