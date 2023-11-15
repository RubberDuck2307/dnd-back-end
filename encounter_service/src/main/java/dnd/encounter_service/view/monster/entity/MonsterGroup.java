package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.config.SqlConfig;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = SqlConfig.MONSTER_VIEW_SCHEMA, name = "monster_monster_group")
public class MonsterGroup {
    @EmbeddedId
    private MonsterGroupId id;
    @ManyToOne
    @MapsId("monsterId")
    private Monster monster;

    @Embeddable
    public static class MonsterGroupId implements Serializable {
        private Long monsterId;
        private Long monsterGroupId;
    }

}
