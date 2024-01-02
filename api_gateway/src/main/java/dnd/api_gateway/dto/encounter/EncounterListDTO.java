package dnd.api_gateway.dto.encounter;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class EncounterListDTO {

    private Collection <EncounterDTO> encounters;

    public EncounterListDTO(Collection<EncounterDTO> encounters) {
        this.encounters = encounters;
    }
}
