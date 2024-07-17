package dnd.monster_service.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonsterSenseDTO {

    private String senseName;
    private Short range;
}
