package dnd.monster_service.rpc.mapper;

import dnd.generated.HitDiceOuterClass;
import dnd.generated.MonsterCreateOuterClass;
import dnd.generated.MonsterServiceOuterClass;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.dice.DiceRoll;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MonsterMapper {

    private Map<Float, Integer> parseCrMap (Map <String, Integer> crMap) {
        return crMap.entrySet().stream().collect(
                Collectors.toMap(
                        entry -> Float.parseFloat(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    public MonsterSearchFilter parseMonsterFilters(MonsterServiceOuterClass.MonsterFiltersRpc filtersRpc){
        String name = filtersRpc.getName().isBlank() ? null : filtersRpc.getName();
        String type = filtersRpc.getType().isBlank() ? null : filtersRpc.getType();
        Float cr = filtersRpc.getCr() == 0 ? null : filtersRpc.getCr();
        Long groupId = filtersRpc.getGroupId() == 0 ? null : filtersRpc.getGroupId();
        return new MonsterSearchFilter(name, type, null, null, groupId);
    }

    public MonsterServiceOuterClass.MonsterShortRpc buildMonsterShortRpc(Monster monster){
        return MonsterServiceOuterClass.MonsterShortRpc.newBuilder()
                .setId(monster.getId())
                .setName(monster.getMonsterName())
                .setCr(monster.getCr())
                .setImageUrl(monster.getImageUrl())
                .build();
    }

    public MonsterServiceOuterClass.MonsterShortListRpc buildMonsterShortListRpc(List<Monster> monsters){
        List<MonsterServiceOuterClass.MonsterShortRpc> monstersRpc = monsters.stream()
                .map(this::buildMonsterShortRpc)
                .toList();
        return MonsterServiceOuterClass.MonsterShortListRpc.newBuilder()
                .addAllMonsters(monstersRpc)
                .build();
    }

    public DiceRoll parseHitDice(HitDiceOuterClass.HitDice hitDice){
        DiceRoll diceRoll = new DiceRoll();
        diceRoll.setDice((short) hitDice.getDice());
        diceRoll.setConstant((short) hitDice.getConstant());
        diceRoll.setAmount((short) hitDice.getCount());
        return diceRoll;
    }
}
