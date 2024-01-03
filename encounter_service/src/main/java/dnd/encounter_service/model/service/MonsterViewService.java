package dnd.encounter_service.model.service;

import dnd.encounter_service.model.entity.encounter.Monster;
import dnd.encounter_service.view.monster.MonsterViewRepository;
import dnd.encounter_service.view.monster.entity.MonsterView;
import dnd.encounter_service.view.monster.entity.MonsterViewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonsterViewService implements dnd.encounter_service.model.service.interfaces.MonsterViewService {

    private final MonsterViewRepository monsterRepository;
    private final MonsterViewMapper monsterViewMapper;

    @Override
    public List<Float> getCrsByMonsterGroup(long monsterGroupId) {
        List<Float> crs = monsterRepository.getAvailableCrForMonsterGroup(monsterGroupId);
        if (crs.size() == 0) {
            throw new IllegalArgumentException("No monsters with the given monster group were found");
        }
        return crs;
    }

    @Override
    public List<Monster> getRandomMonstersByCr(float cr, int amountOfMonsters) {
        int availableAmount = monsterRepository.countAllByCrIs(cr);
        if (availableAmount < amountOfMonsters) {
            amountOfMonsters = availableAmount;
        }
        if (amountOfMonsters == 0) {
            return new ArrayList<>();
        }
        int random = (int) (Math.random() * (availableAmount - amountOfMonsters));
        Page<MonsterView> monsters = monsterRepository.getAllByCrIs(cr, PageRequest.of(random, amountOfMonsters));
        return new ArrayList<>(monsters.getContent().stream().map(monsterViewMapper::ViewToModelMonster).toList());
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId) {
        Map<Float, List<MonsterView>> monsters = monsterRepository.getMonstersByCrAmountAndMonsterGroupId(amountOfCrs,
                groupId);
        return monsters.entrySet().stream().collect(
                Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream().map(monsterViewMapper::ViewToModelMonster).toList()
                )
        );
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCrAmount(Map<Float, Integer> amountOfCrs) {
        return getMonstersByCrAndGroup(amountOfCrs, 0);
    }
}
