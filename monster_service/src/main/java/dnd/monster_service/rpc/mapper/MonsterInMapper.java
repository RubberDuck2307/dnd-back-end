package dnd.monster_service.rpc.mapper;

import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
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

    public MonsterSearchFilter parseMonsterFilters(MonsterServiceOuterClass.MonsterFiltersRpc filtersRpc){
        String name = filtersRpc.getName().isBlank() ? null : filtersRpc.getName();
        String type = filtersRpc.getType().isBlank() ? null : filtersRpc.getType();
        Float cr = filtersRpc.getCr() == 0 ? null : filtersRpc.getCr();
        Long groupId = filtersRpc.getGroupId() == 0 ? null : filtersRpc.getGroupId();
        return new MonsterSearchFilter(name, type, cr, groupId);
    }
}
