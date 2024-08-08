package dnd.monster_service.http.dto.monster_group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMonsterGroupDTO {
   private String monsterGroupName;
   private List<Long> monsterIds;
}
