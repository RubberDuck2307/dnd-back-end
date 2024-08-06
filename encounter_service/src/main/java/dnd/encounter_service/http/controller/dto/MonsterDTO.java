package dnd.encounter_service.http.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonsterDTO {

    private long Id;
    private String name;
    private float cr;

}
