package dnd.RestApi.game.creature;

import dnd.RestApi.config.SQLConfig;
import dnd.RestApi.game.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = SQLConfig.LANGUAGE_TABLE, schema = SQLConfig.SCHEMA)
public class Language {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "languages")
    private Set<Monster> monsterLanguages;
}
