package dnd.monster_service.http.dto.monster;

import lombok.Data;

@Data
public class MonsterTypeDTO {

    public long id;
    public String name;


    public MonsterTypeDTO(String name) {
        this.name = name;
    }
}
