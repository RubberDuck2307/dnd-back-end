package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = SQLConfig.MONSTER_REACTIONS_TABLE, schema = SQLConfig.SCHEMA)
public class MonsterReaction {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;
}
