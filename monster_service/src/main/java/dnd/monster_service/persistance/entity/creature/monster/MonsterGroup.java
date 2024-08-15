package dnd.monster_service.persistance.entity.creature.monster;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

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

    public void removeMonster(Long id){
        monsters = monsters.stream().filter(monster -> monster.getId() != id).collect(Collectors.toSet());
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public MonsterGroup(String name, Set<Monster> monsters) {
        this.name = name;
        this.monsters = monsters;
    }
}
