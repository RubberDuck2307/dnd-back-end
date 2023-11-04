package dnd.rest_api.persistance.service.interfaces;

import dnd.rest_api.api.exception_handling.custom_exception.NoSuchEncounterException;
import dnd.rest_api.persistance.model.encounter.Encounter;

import java.util.ArrayList;

public interface EncounterService {
    /**
     * Creates random encounter with given xp, every encounter will have only one kind of monster per cr.
     * For Example: Encounter made of 2 monsters with cr 1 and 2 monsters with cr 2 will have 1 kind of monster with cr
     * 1 and 1 kind of monster with cr 2
     *
     * @param xp                      desired xp for encounter
     * @param amountOfEncounters      amount of encounters to create, the result can contain fewer encounters
     *                                if there are not enough monsters with given xp
     * @param xpTolerance             fraction specifying how much xp can be off from desired xp
     * @param differentKindOfMonsters if true, there will be multiple kinds of monsters in each encounter
     * @param maxAmountOfMonster      maximal amount of monsters in encounter. If set to 1, the method will return encounters
     *                                with monsters that are closest to the given xp, ignoring the xpTolerance
     * @return list of encounters
     * @throws NoSuchEncounterException if there is no possible combination to create encounters with the given xp
     */

    ArrayList<Encounter> createRandomEncounter(int xp, int amountOfEncounters, float xpTolerance,
                                               boolean differentKindOfMonsters, int maxAmountOfMonster);

    /**
     * Creates random encounter with given xp
     *
     * @param xp                        desired xp for encounter
     * @param amountOfEncounters        amount of encounters to create, the result can contain fewer encounters
     *                                  if there are not enough monsters with given xp
     * @param xpTolerance               fraction specifying how much xp can be off from desired xp
     * @param differentKindOfMonsters   if true, there will be multiple kinds of monsters in each encounter
     * @param maxAmountOfMonsters       maximal amount of monsters in encounter. If set to 1, the method will return encounters
     *                                  with monsters that are closest to the given xp, ignoring the xpTolerance
     * @param onlyOneKindOfMonsterPerCr if true, there will be only one kind of monster per cr in each encounter
     *                                  For Example: If true, Encounter made of 2 monsters with cr 1 and 2 monsters with
     *                                  cr 2 will have 1 kind of monster with cr 1 and 1 kind of monster with cr 2
     *                                  If false, the encounter can have multiple kinds of monsters with the same cr
     * @return list of encounters
     * @throws NoSuchEncounterException if there is no possible combination to create encounters with the given xp
     */

    ArrayList<Encounter> createRandomEncounter(int xp, int amountOfEncounters, float xpTolerance,
                                               boolean differentKindOfMonsters, int maxAmountOfMonsters,
                                               boolean onlyOneKindOfMonsterPerCr, Long monsterGroupId);
}
