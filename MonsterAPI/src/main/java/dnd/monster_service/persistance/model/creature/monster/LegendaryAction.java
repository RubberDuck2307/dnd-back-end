package dnd.monster_service.persistance.model.creature.monster;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.LEGENDARY_ACTIONS_TABLE)
@Entity
@ToString
public class LegendaryAction {
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
