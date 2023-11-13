package dnd.monster_service.model;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;

import java.util.List;
import java.util.Map;

public interface MonsterService {

    List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters);
    Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId);
    int getAmountOfMonsterInGroup(long groupId);
    List<Float> getCrsByMonsterGroup(long groupId);
    Map<Float, List<Monster>> getMonstersByCr(Map<Float, Integer> amountOfCrs);
    List<Monster> getMonsters(int pageSize, int pageNumber, MonsterSearchFilter monsterSearchFilter);

    List<Monster> getMonsters(int pageSize, int pageNumber);

}
