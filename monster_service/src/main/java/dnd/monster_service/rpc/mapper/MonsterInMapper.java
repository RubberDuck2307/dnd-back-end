package dnd.monster_service.rpc.mapper;

import dnd.monster_service.persistance.model.creature.monster.Monster;
import dnd.monster_service.rpc.server.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MonsterInMapper {
    public Map<Float, Integer> parseGetMonstersCrGroupRequest(MonsterServiceOuterClass.
                                                                            GetMonstersCrGroupRequestRpc monsters){
      return  monsters.getCrsMap().entrySet().stream().collect(
                Collectors.toMap(
                        entry -> Float.parseFloat(entry.getKey()),
                        Map.Entry::getValue
                ));

    }

}
