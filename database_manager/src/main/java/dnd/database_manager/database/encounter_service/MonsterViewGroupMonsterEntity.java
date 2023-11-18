package dnd.database_manager.database.encounter_service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MonsterViewGroupMonsterEntity {
    private long groupId;
    private long monsterId;
}
