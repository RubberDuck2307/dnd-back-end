package dnd.RestApi.game.dice;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class DiceRolls {

    private HashMap<Integer, Integer> rolls;
    private int constant;
    public DiceRolls(HashMap<Integer, Integer> rolls, int constant) {
        this.rolls = rolls;
        this.constant = constant;
    }
    public DiceRolls() {
        rolls = new HashMap<>();
    }

    public void addRoll(Integer dice, Integer roll) {
        rolls.put(dice, roll);
    }

    public Integer roll(){
        int sum = 0;

        for (Integer roll : rolls.values()) {
            sum += (int) (Math.random() * roll + 1);
        }
        return sum + constant;
    }

    public int getConstant() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant = constant;
    }
}
