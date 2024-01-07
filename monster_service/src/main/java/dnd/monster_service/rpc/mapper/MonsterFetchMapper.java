package dnd.monster_service.rpc.mapper;

import dnd.generated.AbilityOuterClass;
import dnd.generated.AbilityScoreOuterClass;
import dnd.generated.MonsterCreateOuterClass;
import dnd.monster_service.persistance.entity.creature.Ability;
import dnd.monster_service.persistance.entity.creature.CreatureSize;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.monster.ability_score.MonsterAbilityScore;
import dnd.monster_service.persistance.repository.AbilityRepository;
import dnd.monster_service.persistance.repository.CreatureSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is used to map those data from the rpc request to the model classes that require
 * fetching additional data from database.
 */
@Component
@RequiredArgsConstructor
public class MonsterFetchMapper extends MonsterMapper {

    private final CreatureSizeRepository creatureSizeRepository;
    private final AbilityRepository abilityRepository;

    public Monster parseMonsterCreate(MonsterCreateOuterClass.MonsterCreate monsterCreateRpc) {
        Monster monster = new Monster();
        monster.setMonsterName(monsterCreateRpc.getName());
        monster.setCr(monsterCreateRpc.getCr());
        monster.setImageUrl(monsterCreateRpc.getImageUrl());
        monster.setDescription(monsterCreateRpc.getDescription());
        monster.setPassivePerception((short) monsterCreateRpc.getPassivePerception());
        monster.setArmorClass((short) monsterCreateRpc.getArmorClass());
        monster.setArmorClassDescription(monsterCreateRpc.getArmorClassDescription());
        monster.setSize(parseCreatureSize(monsterCreateRpc.getSize()));
        monster.setHitDice(parseHitDice(monsterCreateRpc.getHitDice()));

        Set<MonsterAbilityScore> abilityScore = parseAbilityScore(monsterCreateRpc.getAbilityScore()).stream().map(
                ability -> new MonsterAbilityScore(ability, (short) 0)).collect(Collectors.toSet());
        monster.setMonsterAbilityScores(abilityScore);

        return monster;
    }

    private CreatureSize parseCreatureSize(String sizeName) {
        Optional<CreatureSize> creatureSize = creatureSizeRepository.findBySize(sizeName);
        if (creatureSize.isPresent())
            return creatureSize.get();
        else
            throw new IllegalArgumentException("Creature size not found");
    }

    private Set<Ability> parseAbilityScore(AbilityScoreOuterClass.AbilityScore abilityScoreRpc) {
        return abilityScoreRpc.getAbilitiesList().stream().map(this::parseAbility).collect(Collectors.toSet());

    }

    private Ability parseAbility(AbilityOuterClass.Ability abilityRpc) {
        Optional<Ability> abilityOptional = abilityRepository.findByTitle(abilityRpc.getName());
        if (abilityOptional.isPresent())
            return abilityOptional.get();
        else
            throw new IllegalArgumentException("Ability: " + abilityRpc.getName() + " not found");
    }

}
