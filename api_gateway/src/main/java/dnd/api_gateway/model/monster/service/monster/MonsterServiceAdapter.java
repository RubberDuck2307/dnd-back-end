package dnd.api_gateway.model.monster.service.monster;

import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.generated.monster_service.MonsterServiceOuterClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MonsterServiceAdapter implements MonsterService{

    private final MonsterGrpcClient monsterGrpcClient;
    @Override
    public List<MonsterGetShortDTO> getMonsters(int page, int size, String name, String type, Float cr, Long groupId) {
       MonsterServiceOuterClass.MonsterShortListRpc rpcList = monsterGrpcClient.getMonsters(page, size, name, type, cr,
               groupId);
        return parseMonsterShortListRpc(rpcList);
    }
    private MonsterGetShortDTO parseMonsterShort(MonsterServiceOuterClass.MonsterShortRpc monsterShortRpc){
        return MonsterGetShortDTO.builder()
                .Cr(monsterShortRpc.getCr())
                .id(monsterShortRpc.getId())
                .name(monsterShortRpc.getName())
                .build();
    }
    private List<MonsterGetShortDTO> parseMonsterShortListRpc(MonsterServiceOuterClass.MonsterShortListRpc list){
        return list.getMonstersList().stream()
                .map(this::parseMonsterShort)
                .collect(Collectors.toList());
    }
}
