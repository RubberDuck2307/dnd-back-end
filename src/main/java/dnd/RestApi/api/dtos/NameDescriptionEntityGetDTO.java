package dnd.RestApi.api.dtos;

import dnd.RestApi.game.creature.monster.MonsterAction;
import dnd.RestApi.game.creature.monster.MonsterTrait;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NameDescriptionEntityGetDTO {

    private String name;
    private String description;


    public NameDescriptionEntityGetDTO(String description, String name) {
        this.description = description;
        this.name = name;
    }
}
