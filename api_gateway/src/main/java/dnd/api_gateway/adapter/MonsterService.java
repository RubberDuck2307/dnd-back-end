package dnd.api_gateway.adapter;

import dnd.api_gateway.dto.monster.CountMonstersDTO;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;

import java.util.List;

public interface MonsterService {
    List<MonsterGetShortDTO> getMonsters(int page, int amount, String name, String type, Float cr, Long groupId);
    CountMonstersDTO countMonsters();
}
