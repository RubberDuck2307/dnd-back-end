package dnd.monster_service.http.dto.monster;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonsterSpeedDTO {

    private String speedName;
    private Short speed;
}
