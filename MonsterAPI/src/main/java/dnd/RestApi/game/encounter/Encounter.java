package dnd.RestApi.game.encounter;

import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.encounter.encounter_creating.EncounterCreationLogic;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class Encounter {

    private final EncounterCreationLogic encounterCreationLogic;
    private ArrayList<Monster> monsters;


    private int gained_xp;
    private int difficulty_xp;

    public Encounter(EncounterCreationLogic encounterCreationLogic, ArrayList<Monster> monsters){
        this.encounterCreationLogic = encounterCreationLogic;
        this.monsters = monsters;
        this.difficulty_xp = encounterCreationLogic.calculateEncounterDifficultyXp(this);
        this.gained_xp = encounterCreationLogic.calculateEncounterGainedXp(this);
    }


    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
        this.difficulty_xp = encounterCreationLogic.calculateEncounterDifficultyXp(this);
        this.gained_xp = encounterCreationLogic.calculateEncounterGainedXp(this);
    }

    public String toString(){
        return "{ Encounter: { Monsters: [" + monsters.toString() +"]" +
                ", gained_xp: " + gained_xp +
                ", difficulty_xp: " + difficulty_xp + "}";
    }
}
