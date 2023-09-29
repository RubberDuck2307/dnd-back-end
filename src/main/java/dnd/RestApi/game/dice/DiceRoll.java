package dnd.RestApi.game.dice;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class DiceRoll {

    public static final int D4 = 4;
    public static final int D6 = 6;
    public static final int D8 = 8;
    public static final int D10 = 10;
    public static final int D12 = 12;
    public static final int D20 = 20;
    public static final int D100 = 100;

    private final static List<Integer> availableDice = List.of(D4, D6, D8, D10, D12, D20, D100);

    private HashMap<Integer, Integer> rolls;
    private int constant;
    public DiceRoll(HashMap<Integer, Integer> rolls, int constant) {
        this.rolls = rolls;
        this.constant = constant;
    }
    public DiceRoll() {
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
