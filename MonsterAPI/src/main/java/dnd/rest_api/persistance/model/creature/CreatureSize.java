package dnd.rest_api.persistance.model.creature;

import dnd.rest_api.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static dnd.rest_api.config.SQLConfig.SCHEMA;

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
