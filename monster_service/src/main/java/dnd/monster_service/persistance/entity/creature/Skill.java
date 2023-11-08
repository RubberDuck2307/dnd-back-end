package dnd.monster_service.persistance.entity.creature;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = SQLConfig.SKILL_TABLE, schema = SQLConfig.SCHEMA)
@Getter
@Setter
@ToString
public class Skill{
    private String name;
    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ability_id")
    private Ability ability;
}
