package dnd.encounter_service.grpc;

import dnd.encounter_service.model.entity.encounter.Encounter;
import dnd.encounter_service.model.service.interfaces.EncounterService;
import dnd.generated.EncounterServiceGrpc;
import dnd.generated.EncounterServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class EncounterServiceServer extends EncounterServiceGrpc.EncounterServiceImplBase {

    private final EncounterService encounterService;
    private final EncounterGrpcMapper encounterGrpcMapper;

    @Override
    public void generateEncounters(EncounterServiceOuterClass.GenerateEncounterRpc request,
                                   StreamObserver<EncounterServiceOuterClass.EncounterListRpc> responseObserver) {
        List<Encounter> encounters = encounterService.createRandomEncounter(request.getXp(),
                request.getAmountOfEncounters(), request.getXpTolerance(),
                request.getDifferentKindOfMonsters(),request.getMaxAmountOfMonsters(),
                request.getOnlyOneKindOfMonsterPerCr(), request.getMonsterGroupId());

        EncounterServiceOuterClass.EncounterListRpc encounterListRpc = encounterGrpcMapper.
                buildEncounterListRpc(encounters);

        responseObserver.onNext(encounterListRpc);
        responseObserver.onCompleted();

    }
}
