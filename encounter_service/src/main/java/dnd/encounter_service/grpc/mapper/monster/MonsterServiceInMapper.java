package dnd.encounter_service.grpc.mapper.monster;

import dnd.encounter_service.grpc.generated.monster_service.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class MonsterServiceInMapper {

    public MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc buildGetMonstersCrGroupRequestRpc
            (Map<Float, Integer> amountOfCrs, long groupId) {
        Map<String, Integer> map = getCrMap(amountOfCrs);
        return MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc.newBuilder()
                .putAllCrs(map)
                .setGroupId(groupId)
                .build();
    }

    private static Map<String, Integer> getCrMap(Map<Float, Integer> amountOfCrs) {
        Map<String, Integer> map = amountOfCrs.entrySet().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                entry -> entry.getKey().toString(),
                                entry -> entry.getValue()
                        )
                );
        return map;
    }

    public MonsterServiceOuterClass.AmountOfCrRpc buildAmountOfCrRpc(Map<Float, Integer> amountOfCrs) {
        Map<String, Integer> map = getCrMap(amountOfCrs);
        return MonsterServiceOuterClass.AmountOfCrRpc.newBuilder()
                .putAllCrMap(map)
                .build();
    }
}
