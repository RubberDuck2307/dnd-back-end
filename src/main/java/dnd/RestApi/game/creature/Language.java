package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = SQLConfig.LANGUAGE_TABLE, schema = SQLConfig.SCHEMA)
public class Language {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
