package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.config.SqlConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = SqlConfig.SCHEMA, name = SqlConfig.MONSTER_VIEW_GROUP_MONSTER_TABLE)
public class MonsterGroupView {
    @EmbeddedId
    private MonsterGroupId id;
    @ManyToOne
    @MapsId("monsterId")
    @JoinColumn(name = "monster_id")
    private MonsterView monster;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MonsterGroupId implements Serializable {
        private Long monsterId;
        private Long monsterGroupId;
    }

}
