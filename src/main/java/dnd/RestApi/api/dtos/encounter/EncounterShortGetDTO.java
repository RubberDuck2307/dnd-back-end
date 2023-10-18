package dnd.RestApi.api.dtos.encounter;

import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class EncounterShortGetDTO {

    private List<EncounterMonsterAmountDTO> monsters;
    private int difficulty_xp;
    private int gained_xp;

    public EncounterShortGetDTO(Encounter encounter){
        this.monsters = new ArrayList<>();
        Set<Monster> foundMonsters = new HashSet<>();
        encounter.getMonsters().forEach(monster -> {
            if (!foundMonsters.contains(monster)){
                foundMonsters.add(monster);
                monsters.add(new EncounterMonsterAmountDTO(monster, Collections.frequency(encounter.getMonsters(),
                        monster)));
            }
        });
        this.difficulty_xp = encounter.getDifficulty_xp();
        this.gained_xp = encounter.getGained_xp();
    }



}
