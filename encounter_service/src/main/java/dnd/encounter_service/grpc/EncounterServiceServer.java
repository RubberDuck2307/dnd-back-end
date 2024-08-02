package dnd.encounter_service.grpc;

import dnd.encounter_service.model.entity.encounter.Encounter;
import dnd.encounter_service.service.interfaces.EncounterService;
import dnd.exception.GrpcExceptionHandler;
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
    private final GrpcExceptionHandler exceptionHandler;

    @Override
    public void generateEncounters(EncounterServiceOuterClass.GenerateEncounterRequest request,
                                   StreamObserver<EncounterServiceOuterClass.EncounterListRpc> responseObserver) {
        List<Encounter> encounters;
        try {
            encounters = encounterService.createRandomEncounter(request.getXp(),
                    request.getAmountOfEncounters(), request.getXpTolerance(),
                    request.getDifferentKindOfMonsters(), request.getMaxAmountOfMonsters(),
                    request.getOnlyOneKindOfMonsterPerCr(), request.getMonsterGroupId());
        } catch (Exception e) {
            responseObserver.onError(exceptionHandler.buildException(e));
            return;
        }
        EncounterServiceOuterClass.EncounterListRpc encounterListRpc = encounterGrpcMapper.
                buildEncounterListRpc(encounters);
        responseObserver.onNext(encounterListRpc);

        responseObserver.onCompleted();

    }
}
