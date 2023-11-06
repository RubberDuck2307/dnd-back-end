package dnd.encounter_service.grpc.monster_service;


import dnd.encounter_service.grpc.generated.Shared;
import dnd.encounter_service.grpc.generated.monster_service.MonsterServiceGrpc;
import dnd.encounter_service.grpc.generated.monster_service.MonsterServiceOuterClass;
import dnd.encounter_service.grpc.mapper.monster.MonsterServiceInMapper;
import dnd.encounter_service.grpc.mapper.monster.MonsterServiceOutMapper;
import dnd.encounter_service.model.Monster;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MonsterGRpcClient implements MonsterService {

    private final MonsterServiceOutMapper outMapper;
    private final MonsterServiceInMapper inMapper;


    @GrpcClient("monster-service")
    private MonsterServiceGrpc.MonsterServiceBlockingStub stub;


    @Override
    public List<Float> getCrsByMonsterGroup(long monsterGroupId) {
        Shared.IdRpc idRpc = Shared.IdRpc.newBuilder().setId(monsterGroupId).build();
        return outMapper.parseCrListRpc(stub.getCrsByMonsterGroupId(idRpc));
    }

    @Override
    public List<Monster> getRandomMonstersByCr(float cr, int amount) {
        MonsterServiceOuterClass.RandomMonsterRequestRpc requestRpc = MonsterServiceOuterClass.RandomMonsterRequestRpc
                .newBuilder()
                .setCr(cr)
                .setAmount(amount)
                .build();
        return outMapper.parseMonsterShortListRpc(stub.getRandomMonstersByCr(requestRpc));
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCrAndGroup(Map<Float, Integer> amountOfCrs, long groupId) {
        MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc request =
                inMapper.buildGetMonstersCrGroupRequestRpc(amountOfCrs, groupId);
        return outMapper.parseMonstersByCrRpc(stub.getMonstersByCrAndGroup(request));
    }

    @Override
    public Map<Float, List<Monster>> getMonstersByCrAmount(Map<Float, Integer> amountOfCrs) {
        MonsterServiceOuterClass.AmountOfCrRpc amountOfCrRpc = inMapper.buildAmountOfCrRpc(amountOfCrs);
        return outMapper.parseMonstersByCrRpc(stub.getMonstersByCrAmount(amountOfCrRpc));
    }
}
