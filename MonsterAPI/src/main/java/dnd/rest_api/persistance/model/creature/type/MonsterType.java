package dnd.rest_api.persistance.model.creature.type;

import dnd.rest_api.config.SQLConfig;
import dnd.rest_api.persistance.model.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static dnd.rest_api.config.SQLConfig.SCHEMA;

@Entity
@Table(schema = SCHEMA, name = SQLConfig.MONSTER_TYPE_TABLE)
@Setter
@Getter
public class MonsterType {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "types", fetch = FetchType.LAZY)
    private Set<Monster> monsters;

    public MonsterType(String name) {
        this.name = name;
    }

    public MonsterType() {

    }
}
