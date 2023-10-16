package dnd.RestApi.game.encounter.encounter_difficulty;

import dnd.RestApi.utils.ListUtils;
import lombok.Data;
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
    public Integer getXpThreshold(Integer level, EncounterDifficultyName difficultyName) {
        return thresholds.stream().filter(threshold -> threshold.getLevel().equals(level)).
                findFirst().orElseThrow().getDifficulty(difficultyName);
    }

    @Override
    public Double getMultiplier(Integer number) {
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
    public Integer getXp(Double cr) {
        return challengeRatings.stream().filter(challengeRatingSetting -> challengeRatingSetting.getCr() == cr).
                findFirst().orElseThrow(() -> new NoSuchElementException("Invalid cr value")).getXp();
    }

    @Override
    public Double getCr(Integer xp) {
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
    public List<Double> getCRs() {
        return challengeRatings.stream().map(ChallengeRatingSetting::getCr).toList();
    }

}

