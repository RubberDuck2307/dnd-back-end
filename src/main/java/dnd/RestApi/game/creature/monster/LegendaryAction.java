package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.LEGENDARY_ACTIONS_TABLE)
@Entity
public class LegendaryAction {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;
}
