package dnd.monster_service.service;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;

import java.util.List;

public interface MonsterService {

    List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters);
    List<Monster> getMonsters(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter);
    Monster addMonster(Monster monster);
    List<Monster> getMonsters(int pageSize, int pageNumber);
    long getAmountOfMonsters();
}
