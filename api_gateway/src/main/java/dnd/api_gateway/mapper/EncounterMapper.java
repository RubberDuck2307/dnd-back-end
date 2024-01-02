package dnd.api_gateway.mapper;

import dnd.api_gateway.dto.encounter.EncounterDTO;
import dnd.api_gateway.dto.encounter.EncounterListDTO;
import dnd.api_gateway.dto.encounter.MonsterAmountDTO;
import dnd.api_gateway.dto.monster.MonsterGetShortDTO;
import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;
import dnd.generated.EncounterServiceOuterClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EncounterMapper {

    public EncounterServiceOuterClass.GenerateEncounterRequest buildGenerateEncounterRequest
            (GenerateEncounterParams generateEncounterParams) {
        return EncounterServiceOuterClass.GenerateEncounterRequest.newBuilder()
                .setXp(generateEncounterParams.getXp())
                .setAmountOfEncounters(generateEncounterParams.getAmountOfEncounters())
                .setXpTolerance(generateEncounterParams.getXpTolerance())
                .setDifferentKindOfMonsters(generateEncounterParams.isDifferentKindOfMonsters())
                .setMaxAmountOfMonsters(generateEncounterParams.getMaxAmountOfMonsters())
                .setOnlyOneKindOfMonsterPerCr(generateEncounterParams.isOnlyOneKindOfMonsterPerCr())
                .setMonsterGroupId(generateEncounterParams.getMonsterGroupId())
                .build();
    }

    public EncounterListDTO buildEncounterListDTO(EncounterServiceOuterClass.EncounterListRpc encounterListRpc) {
        List<EncounterDTO> encounters = encounterListRpc.getEncountersList()
                .stream().map(this::buildEncounterDTO).toList();
       return new EncounterListDTO(encounters);

    }

    public EncounterDTO buildEncounterDTO(EncounterServiceOuterClass.EncounterRpc encounterRpc) {
        return EncounterDTO.builder()
                .difficultyXp(encounterRpc.getDifficultyXp())
                .gainedXp(encounterRpc.getGainedXp())
                .monsters(buildMonsterAmountDTOList(encounterRpc.getMonstersList())).build();
    }

    public List<MonsterAmountDTO> buildMonsterAmountDTOList(List<EncounterServiceOuterClass.MonsterAmountRpc> monsters) {
        return monsters.stream().map(this::buildMonsterAmountDTO).toList();
    }

    public MonsterAmountDTO buildMonsterAmountDTO(EncounterServiceOuterClass.MonsterAmountRpc monsterAmountRpc) {

        return MonsterAmountDTO.builder()
                .monster(buildMonsterGetShortDTO(monsterAmountRpc.getMonster()))
                .amount(monsterAmountRpc.getAmount())
                .build();
    }

    public MonsterGetShortDTO buildMonsterGetShortDTO(EncounterServiceOuterClass.MonsterRpc monsterRpc) {

        return MonsterGetShortDTO.builder()
                .id(monsterRpc.getMonsterId())
                .name(monsterRpc.getName())
                .Cr(monsterRpc.getCr())
                .build();
    }

}
