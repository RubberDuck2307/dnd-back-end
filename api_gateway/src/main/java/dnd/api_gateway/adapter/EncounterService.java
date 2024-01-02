package dnd.api_gateway.adapter;

import dnd.api_gateway.dto.encounter.EncounterDTO;
import dnd.api_gateway.dto.encounter.EncounterListDTO;
import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;

import java.util.List;

public interface EncounterService {

    List<EncounterDTO> generateEncounters(GenerateEncounterParams param);

}
