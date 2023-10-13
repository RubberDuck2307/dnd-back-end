package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = SQLConfig.MONSTER_REACTIONS_TABLE, schema = SQLConfig.SCHEMA)
public class MonsterReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;
}
