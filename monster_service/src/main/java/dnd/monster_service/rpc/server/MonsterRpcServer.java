package dnd.monster_service.rpc.server;

import dnd.generated.*;
import dnd.mapper.PrimitivesProtoMapper;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.service.MonsterService;
import dnd.monster_service.persistance.repository.monster.MonsterSearchFilter;
import dnd.monster_service.rpc.MonsterExceptionHandler;
import dnd.monster_service.rpc.mapper.MonsterFetchMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.logging.Logger;

@GrpcService
@RequiredArgsConstructor
public class MonsterRpcServer extends MonsterServiceGrpc.MonsterServiceImplBase {

    private final MonsterService monsterService;
    private final MonsterFetchMapper mapper;
    private final MonsterExceptionHandler exceptionHandler;
    private final Logger logger = Logger.getLogger(MonsterRpcServer.class.getName());



    @Override
    public void getRandomMonstersByCr(MonsterServiceOuterClass.RandomMonsterRequestRpc request,
                                      StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
        List<Monster> monsters = monsterService.getRandomMonstersByCr(request.getCr(), request.getAmount());
        MonsterServiceOuterClass.MonsterShortListRpc monsterShortListRpc = mapper.buildMonsterShortListRpc(monsters);
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
            MonsterSearchFilter monsterSearchFilter = mapper.parseMonsterFilters(request.getFilters());
            monsters = monsterService.getMonsters(request.getAmount(), request.getPage(), monsterSearchFilter);
        }
        responseObserver.onNext(mapper.buildMonsterShortListRpc(monsters));
        responseObserver.onCompleted();
    }

    @Override
    public void countMonsters(Primitives.Empty request, StreamObserver<Primitives.Long> responseObserver) {
        try {
            Primitives.Long response = PrimitivesProtoMapper.buildProtoLong(monsterService.getAmountOfMonsters());
            responseObserver.onNext(response);
        }
        catch(Exception e){
                responseObserver.onError(exceptionHandler.buildException(e));
                logger.warning(e.getMessage());
                return;
            }
        responseObserver.onCompleted();
        }

    @Override
    public void createMonster(MonsterCreateOuterClass.MonsterCreate request,
                              StreamObserver<Primitives.Empty> responseObserver) {
        try {
        Monster monster = mapper.parseMonsterCreate(request);
        monsterService.addMonster(monster);
        } catch (Exception e) {
            responseObserver.onError(exceptionHandler.buildException(e));
            logger.warning(e.getMessage());
            return;
        }
        responseObserver.onCompleted();
    }
}
