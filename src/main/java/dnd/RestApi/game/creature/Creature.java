package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.damage.DamageType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
