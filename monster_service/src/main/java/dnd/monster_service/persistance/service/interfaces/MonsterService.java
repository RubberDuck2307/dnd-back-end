package dnd.monster_service.persistance.service.interfaces;

import dnd.monster_service.api.dto.monster.MonsterFullGetDTO;

public interface MonsterService {
    MonsterFullGetDTO getMonsterById(Long id);

    MonsterFullGetDTO getMonsterByName(String name);
}
