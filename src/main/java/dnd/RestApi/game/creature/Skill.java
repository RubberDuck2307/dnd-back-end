package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = SQLConfig.SKILL_TABLE, schema = SQLConfig.SCHEMA)
@Getter
@Setter
public class Skill{
    private String name;
    @GeneratedValue
    @Id
    private Long id;
}
