package dnd.monster_service.http.dto.monster;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LegendaryActionDTO {

    private String actionName;
    private String description;
}
