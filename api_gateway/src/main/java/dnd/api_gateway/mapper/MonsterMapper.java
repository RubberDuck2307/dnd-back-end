package dnd.api_gateway.mapper;


import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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


    public MonsterGetShortDTO buildMonsterGetShortDTO(MonsterServiceOuterClass.MonsterShortRpc monsterShortRpc){
        return MonsterGetShortDTO.builder()
                .Cr(monsterShortRpc.getCr())
                .id(monsterShortRpc.getId())
                .name(monsterShortRpc.getName())
                .build();
    }

    public List<MonsterGetShortDTO> buildMonsterGetShortDTOList(MonsterServiceOuterClass.MonsterShortListRpc list){
        return list.getMonstersList().stream()
                .map(this::buildMonsterGetShortDTO)
                .collect(Collectors.toList());
    }
}
