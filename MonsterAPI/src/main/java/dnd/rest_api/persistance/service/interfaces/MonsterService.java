package dnd.rest_api.persistance.service.interfaces;

import dnd.rest_api.api.dto.monster.MonsterFullGetDTO;

public interface MonsterService {
    MonsterFullGetDTO getMonsterById(Long id);

    MonsterFullGetDTO getMonsterByName(String name);
}
