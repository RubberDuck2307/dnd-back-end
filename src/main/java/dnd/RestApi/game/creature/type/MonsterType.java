package dnd.RestApi.game.creature.type;

import dnd.RestApi.game.creature.monster.Monster;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(schema = "dnd")
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
