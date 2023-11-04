package dnd.monster_service.persistance.model.creature.monster.speeds_of_monsters;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SpeedsOfMonstersKey implements Serializable {

    @Column(name = "monster_id")
    private long monsterId;

    @Column(name = "speed_id")
    private long speedId;

}
