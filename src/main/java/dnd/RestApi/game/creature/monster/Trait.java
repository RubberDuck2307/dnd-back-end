package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.TRAITS_TABLE)
@Setter
@Getter
public class Trait {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

}
