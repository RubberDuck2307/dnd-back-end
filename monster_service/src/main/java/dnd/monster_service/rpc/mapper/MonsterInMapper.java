package dnd.monster_service.rpc.mapper;

import dnd.monster_service.rpc.server.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MonsterInMapper {
    public Map<Float, Integer> parseGetMonstersCrGroupRequest(MonsterServiceOuterClass.
                                                                      GetMonstersCrGroupRequestRpc request) {
        return parseCrMap(request.getCrsMap());
    }

    public Map<Float, Integer> parseAmountOfCr(MonsterServiceOuterClass.AmountOfCrRpc request) {
        return parseCrMap(request.getCrMapMap());
    }

    private Map<Float, Integer> parseCrMap (Map <String, Integer> crMap) {
        return crMap.entrySet().stream().collect(
                Collectors.toMap(
                        entry -> Float.parseFloat(entry.getKey()),
                        Map.Entry::getValue
                ));
    }
}
