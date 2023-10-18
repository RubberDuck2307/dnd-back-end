package dnd.RestApi.api.dtos.sense;

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
