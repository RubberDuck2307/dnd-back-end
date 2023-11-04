package dnd.rest_api.persistance.model.creature;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Creature {
    @ManyToOne(fetch = FetchType.EAGER)
    private CreatureSize size;
    private Short hitPoints;
    private Short ArmorClass;
    private String ArmorClassDescription;
}