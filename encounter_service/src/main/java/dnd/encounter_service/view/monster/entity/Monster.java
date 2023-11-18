package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.config.SqlConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table( schema = SqlConfig.SCHEMA, name = "monster_view")
public class Monster {

    @Id
    private Long id;
    private String name;
    @Column( columnDefinition = "real")
    private Float cr;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MonsterGroup> monsterGroups;

    public Monster(Long id, String name, Float cr, Set<MonsterGroup> monsterGroups) {
        this.id = id;
        this.name = name;
        this.cr = cr;
        monsterGroups = monsterGroups == null ? new HashSet<>() : monsterGroups;

    }

    public Monster() {

    }
}
