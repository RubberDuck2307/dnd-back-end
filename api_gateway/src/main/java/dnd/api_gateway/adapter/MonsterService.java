package dnd.api_gateway.adapter;

import dnd.api_gateway.dto.monster.MonsterCreateDTO;
import dnd.api_gateway.dto.monster.MonsterFullGetDto;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;

import java.util.List;

public interface MonsterService {
    List<MonsterGetShortDTO> getMonsters(int page, int size, String name, String type, Float cr, Long groupId);

    MonsterFullGetDto createMonster(MonsterCreateDTO dto);
}
