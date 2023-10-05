package dnd.RestApi.game.dice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static dnd.RestApi.config.SQLConfig.schema;

@Entity(name = "dice_roll")
@Table(schema = schema)
@Setter
@Getter
public class DiceRoll {

    public static final int D4 = 4;
    public static final int D6 = 6;
    public static final int D8 = 8;
    public static final int D10 = 10;
    public static final int D12 = 12;
    public static final int D20 = 20;
    public static final int D100 = 100;

    private final static List<Integer> availableDice = List.of(D4, D6, D8, D10, D12, D20, D100);

    @Id
    @GeneratedValue
    private long id;
    private int dice;
    private int amount;
    private int constant;

    public DiceRoll(int dice, int amount, int constant) {
        if (!availableDice.contains(dice)) {
            throw new IllegalArgumentException("Dice must be one of the following: " + availableDice);
        }
        this.dice = dice;
        this.amount = amount;
        this.constant = constant;
    }

    public DiceRoll() {

    }

    public int getAverage(){
        return (dice + 1) * amount / 2 + constant;
    }

    public int roll(){
        int sum = 0;
        for (int i = 0; i < amount; i++) {
            sum += (int) (Math.random() * dice + 1);
        }
        sum += constant;
        return sum;
    }

}
