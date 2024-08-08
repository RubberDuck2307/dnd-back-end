package dnd.monster_service.service;

import dnd.monster_service.http.dto.cr.CrRangeDto;
import dnd.monster_service.model.cr.CrRange;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import dnd.monster_service.persistance.repository.monster.MonsterSearchSorting;

import java.util.List;
import java.util.Set;

public interface MonsterService {

    List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters);
    List<Monster> getMonsters(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter);
    List<Monster> getMonsters(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter, MonsterSearchSorting sorting);
    Monster addMonster(Monster monster);
    List<Monster> getMonsters(int pageSize, int pageNumber);
    long getAmountOfMonsters();
    Monster getMonsterById(long id);
    List<Monster> getMonstersByIds(List<Long> ids);
    CrRange getCrRange();
    long getAmountOfMonstersFiltered(MonsterSearchFilter monsterSearchFilter);
}
