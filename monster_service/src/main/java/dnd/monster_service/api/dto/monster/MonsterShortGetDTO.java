package dnd.monster_service.api.dto.monster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MonsterShortGetDTO {
    private long id;
    private String name;
    private double cr;


}
