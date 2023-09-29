package dnd.RestApi.game.creature.monster;

import dnd.RestApi.game.creature.Creature;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Entity
@Table(schema = "dnd")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Monster extends Creature {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Double cr;
    private String monsterName;

    @Builder
    public Monster(String type, Double cr, String monsterName, String size, String description, String alignment) {
        super(size, description, alignment);
        this.type = type;
        this.cr = cr;
        this.monsterName = monsterName;
    }
}
