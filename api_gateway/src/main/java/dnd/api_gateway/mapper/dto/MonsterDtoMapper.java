package dnd.api_gateway.mapper.dto;


import dnd.api_gateway.dto.monster.CountMonstersDTO;
import dnd.api_gateway.dto.monster.MonsterCreateDTO;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.mapper.SharedMapper;
import dnd.generated.MonsterCreateOuterClass;
import dnd.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonsterDtoMapper extends SharedMapper {
    public MonsterGetShortDTO buildMonsterGetShortDTO(MonsterServiceOuterClass.MonsterShortRpc monsterShortRpc) {
        return MonsterGetShortDTO.builder()
                .Cr(monsterShortRpc.getCr())
                .id(monsterShortRpc.getId())
                .name(monsterShortRpc.getName())
                .imageUrl(monsterShortRpc.getImageUrl())
                .build();
    }

    public List<MonsterGetShortDTO> buildMonsterGetShortDTOList(MonsterServiceOuterClass.MonsterShortListRpc list) {
        return list.getMonstersList().stream()
                .map(this::buildMonsterGetShortDTO)
                .collect(Collectors.toList());
    }

    public CountMonstersDTO buildCountMonstersDTO(long amount){
        return new CountMonstersDTO(amount);
    }
    @Deprecated //TODO move this to MonsterProtoMapper
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
