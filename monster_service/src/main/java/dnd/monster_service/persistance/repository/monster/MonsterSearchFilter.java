package dnd.monster_service.persistance.repository.monster;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MonsterSearchFilter{

    private String name;
    private String type;
    private Float minCR;
    private Float maxCR;
    private Long groupId;

}



