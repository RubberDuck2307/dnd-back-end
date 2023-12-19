package dnd.api_gateway.mapper;

import dnd.api_gateway.method_parameters.encounter.GenerateEncounterParams;
import dnd.generated.EncounterServiceOuterClass;
import org.springframework.stereotype.Component;

@Component
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

}
