package dnd.monster_service.http.dto.monster_group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMonsterGroupDTO {
    private Long monsterGroupId;
    private List<Long> addedMonsters;
    private List<Long> removedMonsters;


}
