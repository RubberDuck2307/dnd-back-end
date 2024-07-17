package dnd.monster_service.http.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonsterActionDTO {

    private String actionName;
    private String description;
}
