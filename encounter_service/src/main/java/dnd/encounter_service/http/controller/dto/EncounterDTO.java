package dnd.encounter_service.http.controller.dto;

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
