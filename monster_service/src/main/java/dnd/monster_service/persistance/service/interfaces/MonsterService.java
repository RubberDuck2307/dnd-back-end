package dnd.monster_service.persistance.service.interfaces;

import dnd.monster_service.api.dto.monster.MonsterFullGetDTO;
import dnd.monster_service.persistance.model.creature.monster.Monster;

import java.util.List;
import java.util.Map;

public interface MonsterService {
    MonsterFullGetDTO getMonsterById(Long id);
    MonsterFullGetDTO getMonsterByName(String name);
    List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters);
    Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId);
    int getAmountOfMonsterInGroup(long groupId);
    List<Float> getCrsByMonsterGroup(long groupId);



}
