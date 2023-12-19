package dnd.api_gateway.adapter;

import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;

public interface EncounterService {

    void generateEncounters(GenerateEncounterParams param);

}
