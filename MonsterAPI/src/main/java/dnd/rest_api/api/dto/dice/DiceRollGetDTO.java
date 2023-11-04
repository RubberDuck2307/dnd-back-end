package dnd.rest_api.api.dto.dice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DiceRollGetDTO {

    private short amount;
    private short diceType;
    private short constant;

}
