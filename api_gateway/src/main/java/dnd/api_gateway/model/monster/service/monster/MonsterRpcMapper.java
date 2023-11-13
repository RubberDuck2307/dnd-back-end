package dnd.api_gateway.model.monster.service.monster;

import dnd.api_gateway.generated.monster_service.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

@Component
public class MonsterRpcMapper {

    public MonsterServiceOuterClass.MonsterFiltersRpc buildMonsterFiltersRpc(String name, String type, Float cr,
                                                                             Long groupId) {
        MonsterServiceOuterClass.MonsterFiltersRpc.Builder filtersRpcBuilder
                = MonsterServiceOuterClass.MonsterFiltersRpc.newBuilder();
        if (name != null) filtersRpcBuilder.setName(name);
        if (type != null) filtersRpcBuilder.setType(type);
        if (cr != null) filtersRpcBuilder.setCr(cr);
        if (groupId != null) filtersRpcBuilder.setGroupId(groupId);

        return filtersRpcBuilder.build();
    }
}
