package dnd.api_gateway.adapter;

import dnd.api_gateway.client.MonsterGrpcClient;
import dnd.api_gateway.dto.monster.MonsterCreateDTO;
import dnd.api_gateway.dto.monster.MonsterFullGetDto;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.mapper.MonsterMapper;
import dnd.generated.MonsterServiceOuterClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MonsterServiceAdapter implements MonsterService {

    private final MonsterGrpcClient monsterGrpcClient;
    private final MonsterMapper monsterMapper;
    @Override
    public List<MonsterGetShortDTO> getMonsters(int page, int size, String name, String type, Float cr, Long groupId) {
        MonsterServiceOuterClass.MonsterFiltersRpc filtersRpc = monsterMapper
                .buildMonsterFiltersRpc(name, type, cr, groupId);
       MonsterServiceOuterClass.MonsterShortListRpc rpcList = monsterGrpcClient.getMonsters(page, size, filtersRpc);
        return monsterMapper.buildMonsterGetShortDTOList(rpcList);
    }

    @Override
    public MonsterFullGetDto createMonster(MonsterCreateDTO dto) {
        monsterGrpcClient.createMonster(monsterMapper.buildMonsterCreate(dto));
        return null;
    }
}
