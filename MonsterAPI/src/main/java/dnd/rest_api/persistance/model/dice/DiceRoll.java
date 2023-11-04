package dnd.rest_api.persistance.model.dice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static dnd.rest_api.config.SQLConfig.DICE_TABLE;
import static dnd.rest_api.config.SQLConfig.SCHEMA;

@Entity(name = "dice_roll")
@Table(schema = SCHEMA, name = DICE_TABLE)
@Setter
@Getter
public class DiceRoll {

    public static final short D4 = 4;
    public static final short D6 = 6;
    public static final short D8 = 8;
    public static final short D10 = 10;
    public static final short D12 = 12;
    public static final short D20 = 20;
    public static final short D100 = 100;

    private final static List<Short> availableDice = List.of(D4, D6, D8, D10, D12, D20, D100);

    @Id
    @GeneratedValue
    private long id;
    private short dice;
    private short amount;
    private short constant;

    public DiceRoll(short dice, short amount, short constant) {
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
