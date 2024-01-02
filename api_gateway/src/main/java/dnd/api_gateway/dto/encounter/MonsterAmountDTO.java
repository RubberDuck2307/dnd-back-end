package dnd.api_gateway.dto.encounter;

import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonsterAmountDTO {

    private MonsterGetShortDTO monster;
    private int amount;

    @Builder
    public MonsterAmountDTO(MonsterGetShortDTO monster, int amount) {
        this.monster = monster;
        this.amount = amount;
    }
}
