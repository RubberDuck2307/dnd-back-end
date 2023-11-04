package dnd.rest_api.persistance.model.creature.monster;


import dnd.rest_api.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = SQLConfig.MONSTER_ACTIONS_TABLE, schema = SQLConfig.SCHEMA)
public class MonsterAction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;


}