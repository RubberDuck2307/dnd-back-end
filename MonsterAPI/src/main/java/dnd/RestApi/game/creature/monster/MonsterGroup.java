package dnd.RestApi.game.creature.monster;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(schema = SQLConfig.SCHEMA, name = SQLConfig.MONSTER_GROUP_TABLE)
public class MonsterGroup {
    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "monsterGroups")
    private Set<Monster> monsters;

}
