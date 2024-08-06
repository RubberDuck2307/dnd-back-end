package dnd.encounter_service.http.controller.dtomapper;

import dnd.encounter_service.http.controller.dto.EncounterDTO;
import dnd.encounter_service.http.controller.dto.MonsterDTO;
import dnd.encounter_service.model.entity.encounter.Encounter;
import dnd.encounter_service.model.entity.encounter.Monster;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class EncounterDtoMapper {

    public List<EncounterDTO> buildEncounterDtoList(Collection<Encounter> encounters){
        return encounters.stream().map(this::buildEncounterDto).toList();
    }

    public EncounterDTO buildEncounterDto(Encounter encounter){
       return new EncounterDTO(encounter.getMonsters().stream().map(this::buildMonsterDto).toList()
                , encounter.getGainedXp(), encounter.getDifficultyXp());
    }

    public MonsterDTO buildMonsterDto(Monster monster){
       return new MonsterDTO(monster.getId(), monster.getName(), monster.getCr());
    }
}
