package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = SQLConfig.CONDITION_TABLE, schema = SQLConfig.SCHEMA)
public class Condition {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

}
