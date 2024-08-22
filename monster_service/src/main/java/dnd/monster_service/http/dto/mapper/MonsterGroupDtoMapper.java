package dnd.monster_service.http.dto.mapper;

import dnd.monster_service.http.dto.monster_group.GetMonsterGroupDTO;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import org.springframework.stereotype.Component;

@Component
public class MonsterGroupDtoMapper {

    public GetMonsterGroupDTO buildGetMonsterGroupDTO(MonsterGroup monsterGroup){
        return  new GetMonsterGroupDTO(monsterGroup.getName(), monsterGroup.getId());
    }
}
