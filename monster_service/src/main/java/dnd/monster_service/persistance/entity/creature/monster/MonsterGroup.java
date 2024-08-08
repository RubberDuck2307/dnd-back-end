package dnd.monster_service.persistance.entity.creature.monster;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.MONSTER_GROUP_TABLE)
public class MonsterGroup {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "monsterGroups")
    private Set<Monster> monsters;

    public MonsterGroup(String name, Set<Monster> monsters) {
        this.name = name;
        this.monsters = monsters;
    }
}
