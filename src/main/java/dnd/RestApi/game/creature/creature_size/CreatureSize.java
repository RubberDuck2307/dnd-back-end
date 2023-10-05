package dnd.RestApi.game.creature.creature_size;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static dnd.RestApi.config.SQLConfig.schema;

@Entity
@Setter
@Getter
@Table(schema = schema, name = SQLConfig.creature_size_table)
public class CreatureSize {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String size;

}
