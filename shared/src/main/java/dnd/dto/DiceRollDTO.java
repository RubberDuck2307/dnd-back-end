package dnd.dto;

public class DiceRollDTO {

    short numberOfDice;
    short numberOfSides;
    short modifier;

    public DiceRollDTO(short numberOfDice, short numberOfSides, short modifier) {
        this.numberOfDice = numberOfDice;
        this.numberOfSides = numberOfSides;
        this.modifier = modifier;
    }

    public short getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(short numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public short getNumberOfSides() {
        return numberOfSides;
    }

    public void setNumberOfSides(short numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public short getModifier() {
        return modifier;
    }

    public void setModifier(short modifier) {
        this.modifier = modifier;
    }
}
