package dnd.api_gateway.client;



import dnd.api_gateway.mapper.MonsterMapper;
import dnd.generated.MonsterServiceGrpc;
import dnd.generated.MonsterServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;


@Component
public class MonsterGrpcClient {
    @GrpcClient("monster-service")
    private MonsterServiceGrpc.MonsterServiceBlockingStub monsterStub;
    private final MonsterMapper monsterMapper;

    public MonsterGrpcClient(MonsterMapper monsterMapper) {
        this.monsterMapper = monsterMapper;
    }

    public MonsterServiceOuterClass.MonsterShortListRpc getMonsters(int page, int size,
                                                                    MonsterServiceOuterClass.MonsterFiltersRpc filtersRpc) {


        return monsterStub.getMonsters(MonsterServiceOuterClass.GetMonstersRequestRpc.newBuilder()
                .setAmount(size)
                .setPage(page)
                .setFilters(filtersRpc)
                .build());
    }


}
