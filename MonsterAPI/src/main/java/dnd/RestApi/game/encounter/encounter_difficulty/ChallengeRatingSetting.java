package dnd.RestApi.game.encounter.encounter_difficulty;

import lombok.Data;
import lombok.Getter;


@Getter
@Data
public class ChallengeRatingSetting {

    private double cr;
    private int xp;

}
