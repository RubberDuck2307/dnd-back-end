package dnd.RestApi.api.dtos.encounter;

import dnd.RestApi.api.dtos.monster.MonsterShortGetDTO;
import dnd.RestApi.game.creature.monster.Monster;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EncounterMonsterAmountDTO {

    private MonsterShortGetDTO monster;
    private int amount;


    public EncounterMonsterAmountDTO(Monster monster, int amount) {
        this.monster = new MonsterShortGetDTO(monster);
        this.amount = amount;
    }

}
