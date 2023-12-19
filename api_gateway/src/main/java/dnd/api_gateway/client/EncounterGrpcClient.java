package dnd.api_gateway.client;

import dnd.api_gateway.mapper.EncounterMapper;
import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;
import dnd.generated.EncounterServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncounterGrpcClient {

    @GrpcClient("encounter-service")
    private EncounterServiceGrpc.EncounterServiceBlockingStub encounterStub;
    private final EncounterMapper encounterMapper;

    public void generateEncounters(GenerateEncounterParams param) {
        encounterStub.generateEncounters(encounterMapper.buildGenerateEncounterRequest(param));
    }
}
