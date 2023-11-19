package dnd.encounter_service.grpc.mapper.monster;

import dnd.encounter_service.grpc.generated.monster_service.MonsterServiceOuterClass;
import dnd.encounter_service.model.entity.encounter.Monster;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MonsterServiceOutMapper {

    public float parseCrRpc(MonsterServiceOuterClass.CrRpc crRpc) {
        return crRpc.getCr();
    }

    public List<Float> parseCrListRpc(MonsterServiceOuterClass.CrListRpc crListRpc) {
        return crListRpc.getCrsList().stream().map(this::parseCrRpc).toList();
    }

    public Monster parseMonsterShortRpc(MonsterServiceOuterClass.MonsterShortRpc monsterShortRpc) {
        return new Monster(monsterShortRpc.getId(), monsterShortRpc.getName(), monsterShortRpc.getCr());
    }

    public List<Monster> parseMonsterShortListRpc(MonsterServiceOuterClass.MonsterShortListRpc monsterShortListRpc) {
        return monsterShortListRpc.getMonstersList().stream().map(this::parseMonsterShortRpc).toList();
    }

    public Map<Float, List<Monster>> parseMonstersByCrRpc(MonsterServiceOuterClass.MonstersByCrRpc monstersByCrRpc) {
        return monstersByCrRpc.getMonstersMap().entrySet().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                entry -> Float.parseFloat(entry.getKey()),
                                entry -> parseMonsterShortListRpc(entry.getValue())
                        )
                );
    }

}
