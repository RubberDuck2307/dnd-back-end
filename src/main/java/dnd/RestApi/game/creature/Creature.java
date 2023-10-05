package dnd.RestApi.game.creature;

import dnd.RestApi.game.creature.creature_size.CreatureSize;
import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Creature {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CreatureSize size;
    private String description;
    private String alignment;




}
