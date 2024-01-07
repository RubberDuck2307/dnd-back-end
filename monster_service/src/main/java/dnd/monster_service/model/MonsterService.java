package dnd.monster_service.model;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;

import java.util.List;
import java.util.Map;

public interface MonsterService {

    List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters);
    List<Monster> getMonsters(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter);
    Monster addMonster(Monster monster);
    List<Monster> getMonsters(int pageSize, int pageNumber);

}
