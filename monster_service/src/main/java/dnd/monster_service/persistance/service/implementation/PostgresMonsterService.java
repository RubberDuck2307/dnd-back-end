package dnd.monster_service.persistance.service.implementation;


import dnd.monster_service.persistance.model.creature.monster.Monster;
import dnd.monster_service.persistance.repository.monster.MonsterRepository;
import dnd.monster_service.persistance.service.interfaces.MonsterService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostgresMonsterService implements MonsterService {

    private final MonsterRepository monsterRepository;

    @Override
    public List<Monster> getRandomMonstersByCr(double cr, int amountOfMonsters) {
        Precision.round(cr, 4);
        int amount = monsterRepository.countAllByCrIs(cr);
        if (amount < amountOfMonsters) {
            amountOfMonsters = amount;
        }
        if (amountOfMonsters == 0) {
            return new ArrayList<>();
        }
        int random = (int) (Math.random() * (amount - amountOfMonsters));
        Page<Monster> monsters = monsterRepository.getAllByCrIs(cr, PageRequest.of(random, amountOfMonsters));
        return new ArrayList<>(monsters.getContent());
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId) {
        return monsterRepository.getMonstersByCrAmountAndMonsterGroupId(amountOfCrs, groupId);
    }

    @Override
    public int getAmountOfMonsterInGroup(long groupId) {
        return monsterRepository.countByMonsterGroupId(groupId);
    }

    @Override
    public List<Float> getCrsByMonsterGroup(long groupId) {
        List<Float> avCrs;
        avCrs = monsterRepository.getAllCrWhereMonsterGroupsContaining_Id(groupId);
        return avCrs;
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCr(Map<Float, Integer> amountOfCrs) {
       return getMonstersByCrAndGroup(amountOfCrs, 0);
    }


}
