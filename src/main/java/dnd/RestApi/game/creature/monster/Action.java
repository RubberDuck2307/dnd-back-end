package dnd.RestApi.game.creature.monster;


import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = SQLConfig.ACTIONS_TABLE, schema = SQLConfig.SCHEMA)
public class Action {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;


}
