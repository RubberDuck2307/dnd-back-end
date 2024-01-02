package dnd.api_gateway.dto.encounter;

import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Setter
@Getter

public class EncounterDTO {
    private List<MonsterAmountDTO> monsters;
    private int gainedXp;
    private int difficultyXp;

    @Builder
    public EncounterDTO(List<MonsterAmountDTO> monsters, int gainedXp, int difficultyXp) {
        this.monsters = monsters;
        this.gainedXp = gainedXp;
        this.difficultyXp = difficultyXp;
    }
}
