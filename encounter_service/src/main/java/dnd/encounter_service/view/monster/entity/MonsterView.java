package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.config.SqlConfig;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table( schema = SqlConfig.SCHEMA, name = SqlConfig.MONSTER_VIEW_TABLE)
public class MonsterView {

    @Id
    private Long id;
    private String name;
    @Column( columnDefinition = "real")
    private Float cr;
    @OneToMany(mappedBy = "monster", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MonsterGroupView> monsterGroups;

    public MonsterView(Long id, String name, Float cr, Set<MonsterGroupView> monsterGroups) {
        this.id = id;
        this.name = name;
        this.cr = cr;
        monsterGroups = monsterGroups == null ? new HashSet<>() : monsterGroups;

    }

    public void addMonsterGroup(MonsterGroupView monsterGroup){
        monsterGroups.add(monsterGroup);
    }
    public MonsterView() {

    }
}
