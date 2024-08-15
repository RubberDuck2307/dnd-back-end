package dnd.encounter_service.kafka.monster_group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class MonsterGroupMessage {
    public Long monsterGroupId;
    public List<Long> monsterIds;
}
