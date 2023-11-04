package dnd.monster_service.persistance.model.creature.type;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.model.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static dnd.monster_service.config.SQLConfig.SCHEMA;

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
