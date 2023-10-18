package dnd.RestApi.game.encounter.encounter_creating;

import dnd.RestApi.api.exception_handling.custom_exception.NoSuchEncounterException;
import dnd.RestApi.game.encounter.Encounter;

import java.util.ArrayList;
import java.util.List;

public interface EncounterCreationLogic {

    int calculateEncounterDifficultyXp(Encounter encounter);

    int calculateEncounterGainedXp(Encounter encounter);

    /**
     * Creates random encounter with given xp
     * @param xp desired xp for encounter
     * @param amountOfEncounters amount of encounters to create, the result can contain fewer encounters
     *                           if there are not enough monsters with given xp
     * @param xpTolerance fraction specifying how much xp can be off from desired xp
     * @param differentKindOfMonsters if true, there will be multiple kinds of monsters in each encounter
     * @param maxAmountOfMonster maximal amount of monsters in encounter. If set to 1, the method will return encounters
     *                           with monsters that are closest to the given xp, ignoring the xpTolerance
     * @param onlyOneKindOfMonsterPerCr if true, there will be only one kind of monster per cr in each encounter
     *                                  For Example: If true, Encounter made of 2 monsters with cr 1 and 2 monsters with
     *                                  cr 2 will have 1 kind of monster with cr 1 and 1 kind of monster with cr 2
     *                                  If false, the encounter can have multiple kinds of monsters with the same cr
     * @return list of encounters
     * @throws NoSuchEncounterException if there is no possible combination to create encounters with the given xp
     */
    ArrayList<Encounter> createRandomEncounter(int xp, int amountOfEncounters, float xpTolerance,
                                               boolean differentKindOfMonsters, int maxAmountOfMonster,
                                               boolean onlyOneKindOfMonsterPerCr, Long monsterGroupId);

    /**
     * Creates random encounter with given xp, every encounter will have only one kind of monster per cr.
     * For Example: Encounter made of 2 monsters with cr 1 and 2 monsters with cr 2 will have 1 kind of monster with cr
     * 1 and 1 kind of monster with cr 2
     * @param xp desired xp for encounter
     * @param amountOfEncounters amount of encounters to create, the result can contain fewer encounters
     *                           if there are not enough monsters with given xp
     * @param xpTolerance fraction specifying how much xp can be off from desired xp
     * @param differentKindOfMonsters if true, there will be multiple kinds of monsters in each encounter
     * @param maxAmountOfMonster maximal amount of monsters in encounter. If set to 1, the method will return encounters
     *                           with monsters that are closest to the given xp, ignoring the xpTolerance
     * @return list of encounters
     * @throws NoSuchEncounterException if there is no possible combination to create encounters with the given xp
     */
    ArrayList<Encounter> createRandomEncounter(int xp, int amountOfEncounters, float xpTolerance,
                                               boolean differentKindOfMonsters, int maxAmountOfMonster
                                               );



    /**
     * Calculates all possible combinations of crs that add up to xp
     * @param xp desired xp for encounter
     * @param maxAmountOfMonsters maximal amount of monsters in encounter
     * @param availableCrList list of available crs for the encounters
     * @param xpTolerance fraction specifying how much xp can be off from desired xp.
     *                    Example: 0.1 means that xp can be 10% off from desired xp
     * @return list of lists of crs that add up to xp each list represents one possible encounter
     */
    ArrayList<ArrayList<Double>> getCrsForEncounter(int xp, int maxAmountOfMonsters, List<Double> availableCrList,
                                                     float xpTolerance);


}
