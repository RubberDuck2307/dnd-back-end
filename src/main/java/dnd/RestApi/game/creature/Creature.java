package dnd.RestApi.game.creature;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.HashMap;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Creature {

    private String size;
    private String description;
    private String alignment;




}
