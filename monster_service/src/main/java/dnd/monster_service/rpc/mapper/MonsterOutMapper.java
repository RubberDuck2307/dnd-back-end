package dnd.monster_service.rpc.mapper;

import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.rpc.server.generated.MonsterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MonsterOutMapper {

    public MonsterServiceOuterClass.CrRpc buildCrRpc(float cr) {
       return MonsterServiceOuterClass.CrRpc.newBuilder()
                .setCr( cr)
                .build();
    }

    public MonsterServiceOuterClass.CrListRpc buildCrListRpc(List<Float> crs){
        List<MonsterServiceOuterClass.CrRpc> crsRpc = crs.stream().map(this::buildCrRpc).toList();
        return MonsterServiceOuterClass.CrListRpc.newBuilder()
                .addAllCrs(crsRpc)
                .build();
    }

    public MonsterServiceOuterClass.MonsterShortRpc buildMonsterShortRpc(Monster monster){
        return MonsterServiceOuterClass.MonsterShortRpc.newBuilder()
                .setId(monster.getId())
                .setName(monster.getMonsterName())
                .setCr(monster.getCr())
                .build();
    }

    public MonsterServiceOuterClass.MonsterShortListRpc buildMonsterShortListRpc(List<Monster> monsters){
        List<MonsterServiceOuterClass.MonsterShortRpc> monstersRpc = monsters.stream()
                .map(this::buildMonsterShortRpc)
                .toList();
        return MonsterServiceOuterClass.MonsterShortListRpc.newBuilder()
                .addAllMonsters(monstersRpc)
                .build();
    }

    public MonsterServiceOuterClass.MonstersByCrRpc buildMonstersByCrRpc(Map<Float, List<Monster>> monsterMap){
        Map<String, MonsterServiceOuterClass.MonsterShortListRpc> map = monsterMap.entrySet().stream()
            .collect(
                        Collectors.toMap(
                                entry -> entry.getKey().toString(),
                                entry -> buildMonsterShortListRpc(entry.getValue())
                        )
                );
        return MonsterServiceOuterClass.MonstersByCrRpc.newBuilder().putAllMonsters(map).build();
    }

}
