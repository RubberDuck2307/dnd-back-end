package dnd.rest_api.api.dto.sense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SenseGetDTO {

    private String name;
    private short rangeInFeet;

}
