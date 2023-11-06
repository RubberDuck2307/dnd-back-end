package dnd.api_gateway.service.monster;


import dnd.api_gateway.generated.monster_service.MonsterServiceGrpc;
import dnd.api_gateway.generated.monster_service.MonsterServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;


@Component
public class MonsterGrpcClient {
    @GrpcClient("monster-service")
    private MonsterServiceGrpc.MonsterServiceBlockingStub monsterServiceBlockingStub;

    public void getMonsters(int page, int size){
        monsterServiceBlockingStub.getMonsters(MonsterServiceOuterClass.getMonstersRequestRpc.newBuilder()
                .setAmount(size)
                .setPage(page)
                .build()).getMonstersList().forEach(System.out::println);
    }



}
