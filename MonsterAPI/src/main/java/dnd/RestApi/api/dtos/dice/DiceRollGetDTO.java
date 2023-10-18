package dnd.RestApi.api.dtos.dice;

import dnd.RestApi.game.dice.DiceRoll;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiceRollGetDTO {

    private short amount;
    private short diceType;
    private short constant;

    public DiceRollGetDTO(DiceRoll diceRoll) {
        this.amount = diceRoll.getAmount();
        this.diceType = diceRoll.getDice();
        this.constant = diceRoll.getConstant();
    }
}
