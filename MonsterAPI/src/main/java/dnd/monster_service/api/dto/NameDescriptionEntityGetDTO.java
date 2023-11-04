package dnd.monster_service.api.dto;

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
