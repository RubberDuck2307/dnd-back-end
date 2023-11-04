package dnd.monster_service.api.dto.encounter;

import dnd.monster_service.api.dto.monster.MonsterDTOMapper;
import dnd.monster_service.persistance.model.encounter.Encounter;
import dnd.monster_service.persistance.model.creature.monster.Monster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class EncounterDTOMapper {

    private final MonsterDTOMapper monsterDTOMapper;


    public EncounterShortGetDTO getEncounterShortGetDTO(Encounter encounter) {
        List<EncounterMonsterAmountDTO> monsters = new ArrayList<>();
        Set<Monster> foundMonsters = new HashSet<>();

        encounter.getMonsters().forEach(monster -> {
            if (!foundMonsters.contains(monster)) {
                foundMonsters.add(monster);

                monsters.add(new EncounterMonsterAmountDTO(monsterDTOMapper.getMonsterShortGetDTO(monster)
                        , Collections.frequency(encounter.getMonsters(), monster)));
            }
        });
        return new EncounterShortGetDTO(monsters, encounter.getDifficultyXp(), encounter.getGainedXp());
    }
}


