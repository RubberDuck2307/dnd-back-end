package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.SPEED_TABLE)
@Getter
@Setter
public class Speed {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
