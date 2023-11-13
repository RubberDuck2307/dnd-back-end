package dnd.api_gateway.model.monster.service.monster;


import dnd.api_gateway.generated.monster_service.MonsterServiceGrpc;
import dnd.api_gateway.generated.monster_service.MonsterServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;


@Component
public class MonsterGrpcClient {
    @GrpcClient("monster-service")
    private MonsterServiceGrpc.MonsterServiceBlockingStub monsterServiceBlockingStub;
    private final MonsterRpcMapper monsterRpcMapper;

    public MonsterGrpcClient(MonsterRpcMapper monsterRpcMapper) {
        this.monsterRpcMapper = monsterRpcMapper;
    }

    public MonsterServiceOuterClass.MonsterShortListRpc getMonsters(int page, int size, String name,
                                                                    String type, Float cr, Long groupId) {

        MonsterServiceOuterClass.MonsterFiltersRpc filtersRpc = monsterRpcMapper
                .buildMonsterFiltersRpc(name, type, cr, groupId);

        return monsterServiceBlockingStub.getMonsters(MonsterServiceOuterClass.GetMonstersRequestRpc.newBuilder()
                .setAmount(size)
                .setPage(page)
                .setFilters(filtersRpc)
                .build());
    }


}
