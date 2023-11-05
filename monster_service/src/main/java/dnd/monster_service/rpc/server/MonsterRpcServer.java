package dnd.monster_service.rpc.server;

import dnd.monster_service.persistance.model.creature.monster.Monster;
import dnd.monster_service.persistance.service.interfaces.MonsterService;
import dnd.monster_service.rpc.mapper.MonsterInMapper;
import dnd.monster_service.rpc.mapper.MonsterOutMapper;
import dnd.monster_service.rpc.server.generated.MonsterServiceGrpc;
import dnd.monster_service.rpc.server.generated.MonsterServiceOuterClass;
import dnd.monster_service.rpc.server.generated.Shared;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@GrpcService
@RequiredArgsConstructor
public class MonsterRpcServer extends MonsterServiceGrpc.MonsterServiceImplBase {

    private final MonsterService monsterService;
    private final MonsterOutMapper monsterOutMapper;
    private final MonsterInMapper monsterInMapper;

    @Override
    public void getCrsByMonsterGroupId(Shared.IdRpc request,
                                       StreamObserver<MonsterServiceOuterClass.CrListRpc> responseObserver) {
        List<Float> monsterGroupCrs = monsterService.getCrsByMonsterGroup(request.getId());
        MonsterServiceOuterClass.CrListRpc crListRpc = monsterOutMapper.buildCrListRpc(monsterGroupCrs);
        responseObserver.onNext(crListRpc);
        responseObserver.onCompleted();

    }

    @Override
    public void getAmountOfMonsterByGroupId(Shared.IdRpc request, StreamObserver<Shared.AmountRpc> responseObserver) {
        int amountOfMonster = monsterService.getAmountOfMonsterInGroup(request.getId());
        Shared.AmountRpc amountRpc = Shared.AmountRpc.newBuilder()
                .setAmount(amountOfMonster)
                .build();
        responseObserver.onNext(amountRpc);
        responseObserver.onCompleted();
    }

    @Override
    public void getRandomMonstersByCr(MonsterServiceOuterClass.RandomMonsterRequestRpc request,
                                      StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
        List<Monster> monsters = monsterService.getRandomMonstersByCr(request.getCr(), request.getAmount());
        System.out.println(request.getAmount());
        MonsterServiceOuterClass.MonsterShortListRpc monsterShortListRpc = monsterOutMapper.buildMonsterShortListRpc(monsters);
        responseObserver.onNext(monsterShortListRpc);
        responseObserver.onCompleted();
    }

    @Override
    public void getMonstersByCrAndGroup(MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc request,
                                        StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc> responseObserver) {
        Map<Float, List<Monster>> monsters = monsterService.getMonstersByCrAndGroup(monsterInMapper.
                parseGetMonstersCrGroupRequest(request), request.getGroupId());
        MonsterServiceOuterClass.MonstersByCrRpc monstersByCrRpc = monsterOutMapper.buildMonstersByCrRpc(monsters);
        responseObserver.onNext(monstersByCrRpc);
        responseObserver.onCompleted();
    }
}
