package dnd.monster_service.persistance.model.creature;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.model.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
