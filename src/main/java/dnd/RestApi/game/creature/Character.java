package dnd.RestApi.game.creature;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static dnd.RestApi.config.SQLConfig.SCHEMA;

@Data
@Entity
@Table(schema = SCHEMA)
public class Character extends Creature{

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String classType;
    private String level;


}
