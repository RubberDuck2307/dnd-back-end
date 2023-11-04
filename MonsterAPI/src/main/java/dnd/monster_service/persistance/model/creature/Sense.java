package dnd.monster_service.persistance.model.creature;

import dnd.monster_service.config.SQLConfig;
import dnd.monster_service.persistance.model.creature.monster.sense.MonsterSense;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity

@Table(name = SQLConfig.SENSE_TABLE, schema = SQLConfig.SCHEMA)
public class Sense {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(mappedBy = "sense" , fetch = FetchType.LAZY)
    private Set<MonsterSense> monsters;
}
