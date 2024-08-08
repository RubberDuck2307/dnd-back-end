package dnd.encounter_service.http.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class EncounterDTO {
    List<MonsterDTO> monsters;
    int gainedXp;
    int difficultyXp;
}
