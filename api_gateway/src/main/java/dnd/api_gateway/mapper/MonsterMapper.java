package dnd.api_gateway.mapper;


import dnd.api_gateway.dto.AbilityScoreDto;
import dnd.api_gateway.dto.monster.MonsterCreateDTO;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.generated.AbilityOuterClass;
import dnd.generated.AbilityScoreOuterClass;
import dnd.generated.MonsterCreateOuterClass;
import dnd.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonsterMapper extends SharedMapper{

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
                .imageUrl(monsterShortRpc.getImageUrl())
                .build();
    }

    public List<MonsterGetShortDTO> buildMonsterGetShortDTOList(MonsterServiceOuterClass.MonsterShortListRpc list){
        return list.getMonstersList().stream()
                .map(this::buildMonsterGetShortDTO)
                .collect(Collectors.toList());
    }

    public MonsterCreateOuterClass.MonsterCreate buildMonsterCreate(MonsterCreateDTO dto) {
        return MonsterCreateOuterClass.MonsterCreate.newBuilder()
                .setName(dto.getName())
                .setCr(dto.getCr())
                .setImageUrl(dto.getImageUrl())
                .setSize(dto.getSize())
                .setDescription(dto.getDescription())
                .setAbilityScore(buildAbilityScore(dto.getAbilityScore()))
                .build();
    }
}
