package dnd.api_gateway.method_parameters.encounter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenerateEncounterParams {

    public int xp;
    public int amountOfEncounters;
    public float xpTolerance;
    public boolean differentKindOfMonsters;
    public int maxAmountOfMonsters;
    public boolean onlyOneKindOfMonsterPerCr;
    public long monsterGroupId;
}
