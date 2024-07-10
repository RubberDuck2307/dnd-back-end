package dnd.api_gateway.client;

import dnd.api_gateway.mapper.EncounterMapper;
import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;
import dnd.generated.EncounterServiceGrpc;
import dnd.generated.EncounterServiceOuterClass;
import dnd.generated.Shared;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Deprecated //TODO remove, just use the adapter class
public class EncounterGrpcClient {

    @GrpcClient("encounter-service")
    private EncounterServiceGrpc.EncounterServiceBlockingStub encounterStub;
    private final EncounterMapper encounterMapper;

    public EncounterServiceOuterClass.EncounterListRpc generateEncounters(GenerateEncounterParams param) {
            return encounterStub.generateEncounters(encounterMapper.buildGenerateEncounterRequest(param));
    }
}
