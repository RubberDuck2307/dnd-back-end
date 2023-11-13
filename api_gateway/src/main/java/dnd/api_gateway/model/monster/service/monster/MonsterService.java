package dnd.api_gateway.model.monster.service.monster;

import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.model.monster.entity.Monster;

import java.util.List;

public interface MonsterService {
    List<MonsterGetShortDTO> getMonsters(int page, int size, String name, String type, Float cr, Long groupId);
}
