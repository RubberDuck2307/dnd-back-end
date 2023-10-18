package dnd.RestApi.api.dtos.monster;

import dnd.RestApi.game.creature.monster.Monster;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterShortGetDTO {
    private long id;
    private String name;
    private double cr;

    public MonsterShortGetDTO(Monster monster){
        this.id = monster.getId();
        this.name = monster.getMonsterName();
        this.cr = monster.getCr();
    }

}
