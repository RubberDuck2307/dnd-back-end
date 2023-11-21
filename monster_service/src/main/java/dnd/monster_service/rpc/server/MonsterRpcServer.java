package dnd.monster_service.rpc.server;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.model.MonsterService;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import dnd.monster_service.rpc.mapper.MonsterInMapper;
import dnd.monster_service.rpc.mapper.MonsterOutMapper;
import dnd.monster_service.rpc.server.generated.MonsterServiceGrpc;
import dnd.monster_service.rpc.server.generated.MonsterServiceOuterClass;
import dnd.monster_service.rpc.server.generated.Shared;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Map;

@GrpcService
@RequiredArgsConstructor
public class MonsterRpcServer extends MonsterServiceGrpc.MonsterServiceImplBase {

    private final MonsterService monsterService;
    private final MonsterOutMapper monsterOutMapper;
    private final MonsterInMapper monsterInMapper;



    @Override
    public void getRandomMonstersByCr(MonsterServiceOuterClass.RandomMonsterRequestRpc request,
                                      StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
        List<Monster> monsters = monsterService.getRandomMonstersByCr(request.getCr(), request.getAmount());
        MonsterServiceOuterClass.MonsterShortListRpc monsterShortListRpc = monsterOutMapper.buildMonsterShortListRpc(monsters);
        responseObserver.onNext(monsterShortListRpc);
        responseObserver.onCompleted();
    }

    @Override
    public void getMonsters(MonsterServiceOuterClass.GetMonstersRequestRpc request,
                            StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {

        List<Monster> monsters;
        if (!request.hasFilters())
            monsters = monsterService.getMonsters(request.getAmount(), request.getPage());
        else {
            MonsterSearchFilter monsterSearchFilter = monsterInMapper.parseMonsterFilters(request.getFilters());
            monsters = monsterService.getMonsters(request.getAmount(), request.getPage(), monsterSearchFilter);
        }
        responseObserver.onNext(monsterOutMapper.buildMonsterShortListRpc(monsters));
        responseObserver.onCompleted();
    }
}
