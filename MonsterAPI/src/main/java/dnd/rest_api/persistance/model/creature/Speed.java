package dnd.rest_api.persistance.model.creature;

import dnd.rest_api.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.SPEED_TABLE)
@Getter
@ToString
@Setter
public class Speed {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
