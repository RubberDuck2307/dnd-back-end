package dnd.encounter_service.grpc;

import dnd.encounter_service.model.entity.encounter.Encounter;
import dnd.encounter_service.model.entity.encounter.Monster;
import dnd.generated.EncounterServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EncounterGrpcMapper {

    public EncounterServiceOuterClass.EncounterListRpc buildEncounterListRpc(Collection<Encounter> encounters) {
        EncounterServiceOuterClass.EncounterListRpc.Builder encounterListRpcBuilder = EncounterServiceOuterClass
                .EncounterListRpc.newBuilder();
        encounters.forEach(encounter -> encounterListRpcBuilder.addEncounters(buildEncounterRpc(encounter)));
        return encounterListRpcBuilder.build();
    }

    public EncounterServiceOuterClass.EncounterRpc buildEncounterRpc(Encounter encounter) {
        EncounterServiceOuterClass.EncounterRpc.Builder encounterRpcBuilder = EncounterServiceOuterClass.EncounterRpc
                .newBuilder();
        encounterRpcBuilder.setDifficultyXp(encounter.getDifficultyXp());
        encounterRpcBuilder.setGainedXp(encounter.getGainedXp());
        encounterRpcBuilder.addAllMonsters(buildMonsterAmountRpc(encounter.getMonsters()));
        return encounterRpcBuilder.build();
    }

    public ArrayList<EncounterServiceOuterClass.MonsterAmountRpc> buildMonsterAmountRpc(Collection<Monster> monsters) {
        ArrayList<EncounterServiceOuterClass.MonsterAmountRpc> monsterAmountRpcList = new ArrayList<>();

        new HashSet<Monster>() {{
            addAll(monsters);
        }}.forEach(monster -> {
            EncounterServiceOuterClass.MonsterAmountRpc.Builder monsterAmountRpcBuilder = EncounterServiceOuterClass
                    .MonsterAmountRpc.newBuilder();
            int amount = Collections.frequency(monsters, monster);
            monsterAmountRpcBuilder.setAmount(amount);
            monsterAmountRpcBuilder.setMonster(buildMonsterRpc(monster));
            monsterAmountRpcList.add(monsterAmountRpcBuilder.build());
        });
        return monsterAmountRpcList;
    }

    public EncounterServiceOuterClass.MonsterRpc buildMonsterRpc(Monster monster) {
        EncounterServiceOuterClass.MonsterRpc.Builder monsterRpcBuilder = EncounterServiceOuterClass.MonsterRpc
                .newBuilder();
        monsterRpcBuilder.setCr(monster.getCr());
        monsterRpcBuilder.setMonsterId(monster.getId());
        monsterRpcBuilder.setName(monster.getName());
        return monsterRpcBuilder.build();
    }

}
