package dnd.rest_api.persistance.model.creature.monster;

import dnd.rest_api.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.MONSTER_TRAITS_TABLE)
@Setter
@Getter
@ToString
public class MonsterTrait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

}
