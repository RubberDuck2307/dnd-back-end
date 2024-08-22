package dnd.monster_service.http.dto.monster_group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetMonsterGroupDTO {
    private String name;
    private Long id;
}
