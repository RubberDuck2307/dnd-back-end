package dnd.api_gateway.dto.monster;

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
    private Float Cr;
    private  String imageUrl;

}
