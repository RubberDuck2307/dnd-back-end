package dnd.monster_service.http.dto.monster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonsterAbilityScoreDTO {

    private String abilityName;
    private String shortName;
    private Short score;
    private Short savingThrow;
}
