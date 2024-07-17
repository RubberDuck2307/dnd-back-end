package dnd.monster_service.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonsterReactionDTO {

        private String reactionName;
        private String description;
}
