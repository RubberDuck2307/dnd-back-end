package dnd.rest_api.persistance.model.creature;

import dnd.rest_api.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = SQLConfig.CONDITION_TABLE, schema = SQLConfig.SCHEMA)
public class Condition {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

}
