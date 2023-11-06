package dnd.encounter_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Monster {

    private long id;
    private String name;
    private float cr;

}
