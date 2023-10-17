package dnd.RestApi.api.dtos.speed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SpeedGetDTO {

    private String name;
    private short valueInFeet;
}
