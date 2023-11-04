package dnd.rest_api.api.dto.encounter;

import dnd.rest_api.api.dto.monster.MonsterDTOMapper;
import dnd.rest_api.persistance.model.encounter.Encounter;
import dnd.rest_api.persistance.model.creature.monster.Monster;
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

        encounter.getMonsters().forEach(mstr -> {
            if (mstr instanceof Monster monster) {
                if (!foundMonsters.contains(monster)) {
                    foundMonsters.add(monster);

                    monsters.add(new EncounterMonsterAmountDTO(monsterDTOMapper.getMonsterShortGetDTO(monster)
                            , Collections.frequency(encounter.getMonsters(), monster)));
                }
            }
            else {
                throw new IllegalArgumentException("Monsters are not instance of the Monster class");
            }
        });
        return new EncounterShortGetDTO(monsters, encounter.getDifficultyXp(), encounter.getGainedXp());
    }
}


