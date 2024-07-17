package dnd.monster_service.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonsterTraitDTO {
    private String traitName;
    private String description;
}
