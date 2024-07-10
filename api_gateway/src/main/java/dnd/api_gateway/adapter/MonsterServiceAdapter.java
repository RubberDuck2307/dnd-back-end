package dnd.api_gateway.adapter;

import dnd.api_gateway.dto.monster.CountMonstersDTO;
import dnd.api_gateway.dto.monster.MonsterCreateDTO;
import dnd.api_gateway.dto.monster.MonsterFullGetDto;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.mapper.dto.MonsterDtoMapper;
import dnd.api_gateway.mapper.proto.MonsterProtoMapper;
import dnd.generated.MonsterServiceGrpc;
import dnd.generated.MonsterServiceOuterClass;
import dnd.mapper.PrimitivesProtoMapper;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MonsterServiceAdapter implements MonsterService {
    private final MonsterDtoMapper monsterDtoMapper;
    private final MonsterProtoMapper monsterProtoMapper;
    @GrpcClient("monster-service")
    private MonsterServiceGrpc.MonsterServiceBlockingStub monsterStub;

    @Override
    public List<MonsterGetShortDTO> getMonsters(int page, int size, String name, String type, Float cr, Long groupId) {
        MonsterServiceOuterClass.GetMonstersRequestRpc requestRpc = monsterProtoMapper.buildGetMonsterRequestRpc(page,
                size, name, type, cr, groupId);
        MonsterServiceOuterClass.MonsterShortListRpc rpcList = monsterStub.getMonsters(requestRpc);
        return monsterDtoMapper.buildMonsterGetShortDTOList(rpcList);
    }

    @Override
    public CountMonstersDTO countMonsters() {
       long amount =  PrimitivesProtoMapper.parseProtoLong(monsterStub.countMonsters(PrimitivesProtoMapper.buildProtoEmpty()));
        return monsterDtoMapper.buildCountMonstersDTO(amount);
    }

    @Override
    public MonsterFullGetDto createMonster(MonsterCreateDTO dto) {
        monsterStub.createMonster(monsterDtoMapper.buildMonsterCreate(dto));
        return null;
    }
}
