package dnd.api_gateway.adapter;

import dnd.api_gateway.client.EncounterGrpcClient;
import dnd.api_gateway.mapper.EncounterMapper;
import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class is converting the classes returned by the service layer to the classes that are used
 * by the controller layer and vice versa.
 */
@Component
@RequiredArgsConstructor
public class EncounterAdapter implements EncounterService {

    private final EncounterGrpcClient encounterGrpcClient;
    private final EncounterMapper encounterMapper;

    @Override
    public void generateEncounters(GenerateEncounterParams param) {
        encounterGrpcClient.generateEncounters(param);
    }
}
