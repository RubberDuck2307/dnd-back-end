package dnd.api_gateway.dto.monster;

import dnd.api_gateway.dto.AbilityScoreDto;
import lombok.Data;

import java.util.List;

@Data
public class MonsterCreateDTO {

    private String name;
    private List<String> types;
    private Float cr;
    private String ImageUrl;
    private String description;
    private AbilityScoreDto abilityScore;
    private String size;

}
