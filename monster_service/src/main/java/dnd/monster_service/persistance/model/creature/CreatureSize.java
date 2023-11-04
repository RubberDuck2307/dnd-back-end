package dnd.monster_service.persistance.model.creature;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static dnd.monster_service.config.SQLConfig.SCHEMA;

@Entity
@Setter
@Getter
@ToString
@Table(schema = SCHEMA, name = SQLConfig.CREATURE_SIZE_TABLE)
public class CreatureSize {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String size;

}
