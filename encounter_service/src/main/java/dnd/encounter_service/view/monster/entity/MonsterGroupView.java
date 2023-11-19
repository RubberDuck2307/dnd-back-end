package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.config.SqlConfig;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(schema = SqlConfig.SCHEMA, name = SqlConfig.MONSTER_VIEW_GROUP_MONSTER_TABLE)
public class MonsterGroupView {
    @EmbeddedId
    private MonsterGroupId id;
    @ManyToOne
    @MapsId("monsterId")
    @JoinColumn(name = "monster_id")
    private MonsterView monster;

    @Embeddable
    public static class MonsterGroupId implements Serializable {
        private Long monsterId;
        private Long monsterGroupId;
    }

}
