package dnd.api_gateway.mapper;


import dnd.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

@Component
public class MonsterMapper {

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
