package dnd.encounter_service.model.entity.encounter;

import dnd.encounter_service.model.entity.Monster;
import lombok.Getter;

import java.util.ArrayList;

@Getter

public class Encounter {

    private ArrayList<Monster> monsters;
    private int gainedXp;
    private int difficultyXp;

    protected Encounter(ArrayList<Monster> monsters, int gainedXp, int difficultyXp) {
        this.monsters = monsters;
        this.difficultyXp = difficultyXp;
        this.gainedXp = gainedXp;
    }

    protected void setMonsters(ArrayList<Monster> monsters, int gained_xp, int difficulty_xp) {
        this.monsters = monsters;
        this.difficultyXp = difficulty_xp;
        this.gainedXp = gained_xp;
    }

    public String toString() {
        return "{ Encounter: { Monsters: [" + monsters.toString() + "]" +
                ", gained_xp: " + gainedXp +
                ", difficulty_xp: " + difficultyXp + "}";
    }
}
