package dnd.monster_service.http.dto.monster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MonsterGetShortDTO {

    private Long id;
    private String name;
    private Float cr;
    private String imageUrl;
    private List<MonsterTypeDTO> types;

}
