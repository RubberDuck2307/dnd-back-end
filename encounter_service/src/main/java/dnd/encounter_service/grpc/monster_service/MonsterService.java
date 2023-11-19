package dnd.encounter_service.grpc.monster_service;

import dnd.encounter_service.model.entity.encounter.Monster;

import java.util.List;
import java.util.Map;

public interface MonsterService {

    List<Float> getCrsByMonsterGroup(long monsterGroupId);

    List<Monster> getRandomMonstersByCr(float cr, int amount);

    Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId);

    Map<Float, List<Monster>> getMonstersByCrAmount(Map<Float, Integer> amountOfCrs);

}
