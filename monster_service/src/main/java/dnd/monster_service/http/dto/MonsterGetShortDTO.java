package dnd.monster_service.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MonsterGetShortDTO {

    private Long id;
    private String name;
    private Float cr;
    private  String imageUrl;

}
