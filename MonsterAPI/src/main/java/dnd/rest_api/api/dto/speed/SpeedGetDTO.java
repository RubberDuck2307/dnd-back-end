package dnd.rest_api.api.dto.speed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SpeedGetDTO {

    private String name;
    private short valueInFeet;
}
