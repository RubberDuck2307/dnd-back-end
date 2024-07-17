package dnd.monster_service.http.dto.mapper;

import dnd.dto.ConditionDTO;
import dnd.dto.DiceRollDTO;
import dnd.monster_service.http.dto.*;
import dnd.monster_service.persistance.entity.creature.Condition;
import dnd.monster_service.persistance.entity.creature.monster.*;
import dnd.monster_service.persistance.entity.creature.monster.ability_score.MonsterAbilityScore;
import dnd.monster_service.persistance.entity.creature.monster.damage.MonsterDamage;
import dnd.monster_service.persistance.entity.creature.monster.damage.MonsterVulnerability;
import dnd.monster_service.persistance.entity.creature.monster.sense.MonsterSense;
import dnd.monster_service.persistance.entity.creature.monster.skills_of_monsters.SkillsOfMonsters;
import dnd.monster_service.persistance.entity.creature.monster.speeds_of_monsters.SpeedsOfMonsters;
import dnd.monster_service.persistance.entity.creature.type.MonsterType;
import dnd.monster_service.persistance.entity.dice.DiceRoll;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    public GetFullMonsterDTO buildGetFullMonsterDTO(Monster monster) {
        GetFullMonsterDTO.GetFullMonsterDTOBuilder builder = GetFullMonsterDTO.builder();
        builder.id(monster.getId());
        builder.monsterName(monster.getMonsterName());
        builder.cr(monster.getCr());
        builder.armorClass(monster.getArmorClass());
        builder.armorClassDescription(monster.getArmorClassDescription());
        builder.description(monster.getDescription());
        builder.homebrew(monster.getHomebrew());
        builder.imageUrl(monster.getImageUrl());
        builder.passivePerception(monster.getPassivePerception());
        builder.size(monster.getSize().getSize());
        builder.hitPoints(monster.getHitPoints());
        builder.hitDice(buildDiceRollDTO(monster.getHitDice()));
        builder.legendaryActionDescription(monster.getLegendaryActionDescription());
        builder.actions(monster.getActions().stream().map(this::buildMonsterActionDTO).collect(Collectors.toSet()));
        builder.types(monster.getTypes().stream().map(this::buildMonsterTypeDTO).collect(Collectors.toSet()));
        builder.monsterAbilityScores(buildMonsterAbilityScoreDTO(monster));
        builder.speeds(monster.getSpeeds().stream().map(this::buildMonsterSpeedDTO).collect(Collectors.toSet()));
        builder.skills(monster.getSkills().stream().map(this::buildMonsterSkillsDTO).collect(Collectors.toSet()));
        builder.senses(monster.getSenses().stream().map(this::buildMonsterSenseDTO).collect(Collectors.toSet()));
        builder.traits(monster.getTraits().stream().map(this::buildMonsterTraitDTO).collect(Collectors.toSet()));
        builder.damageResistancesAndImmunities(monster.getDamageResistancesAndImmunities().stream()
                .map(this::buildMonsterDamageDTO).collect(Collectors.toSet()));
        builder.damageVulnerabilities(monster.getDamageVulnerabilities().stream().map(this::buildMonsterVulnerabilityDTO)
                .collect(Collectors.toSet()));
        builder.conditionImmunities(monster.getConditionImmunities().stream().map(this::buildConditionDTO)
                .collect(Collectors.toSet()));
        builder.reactions(monster.getReactions().stream().map(this::buildMonsterActionDTO).collect(Collectors.toSet()));
        builder.legendaryActions(monster.getLegendaryActions().stream().map(this::buildLegendaryActionDTO)
                .collect(Collectors.toSet()));

        return builder.build();
    }

    public DiceRollDTO buildDiceRollDTO(DiceRoll diceRoll) {
        return new DiceRollDTO(diceRoll.getAmount(), diceRoll.getDice(), diceRoll.getConstant());
    }

    public MonsterActionDTO buildMonsterActionDTO(MonsterAction action) {
        MonsterActionDTO.MonsterActionDTOBuilder builder = MonsterActionDTO.builder();
        builder.actionName(action.getName());
        builder.description(action.getDescription());
        return builder.build();
    }

    public MonsterTypeDTO buildMonsterTypeDTO(MonsterType type) {
        return new MonsterTypeDTO(type.getName());
    }

    public Set<MonsterAbilityScoreDTO> buildMonsterAbilityScoreDTO(Monster monster) {
        return monster.getMonsterAbilityScores().stream().map(as ->
        {
            MonsterAbilityScoreDTO dto = new MonsterAbilityScoreDTO();
            dto.setAbilityName(as.getAbilityScore().getTitle());
            dto.setShortName(as.getAbilityScore().getAbbreviation());
            dto.setScore(as.getValue());
            monster.getSavingThrows().forEach(savingThrow -> {
                if (Objects.equals(as.getAbilityScore().getId(), savingThrow.getAbilityScore().getId())) {
                    dto.setSavingThrow(savingThrow.getValue());
                }
            });
            return dto;
        }).collect(Collectors.toSet());
    }

    public MonsterSpeedDTO buildMonsterSpeedDTO(SpeedsOfMonsters speed) {
        return new MonsterSpeedDTO(speed.getSpeed().getName(), speed.getValue());
    }

    public MonsterSkillsDTO buildMonsterSkillsDTO(SkillsOfMonsters skill) {
        return new MonsterSkillsDTO(skill.getSkill().getName(), skill.getBonus());
    }

    public MonsterSenseDTO buildMonsterSenseDTO(MonsterSense sense) {
        return new MonsterSenseDTO(sense.getSense().getName(), sense.getRange());
    }

    public MonsterTraitDTO buildMonsterTraitDTO(MonsterTrait trait) {
        return new MonsterTraitDTO(trait.getTitle(), trait.getDescription());
    }

    public MonsterDamageDTO buildMonsterDamageDTO(MonsterDamage damage) {
        return new MonsterDamageDTO(damage.getDamageType().getName(), damage.getAttackTypeException() == null ? null :
                damage.getAttackTypeException().getName(), damage.isImmune());
    }

    public MonsterVulnerabilityDTO buildMonsterVulnerabilityDTO(MonsterVulnerability vulnerability) {
        return new MonsterVulnerabilityDTO(vulnerability.getDamageType().getName(),vulnerability.getAttackType()
                == null ? null : vulnerability.getAttackType().getName());
    }

    public MonsterReactionDTO buildMonsterActionDTO(MonsterReaction monsterAction) {
        return new MonsterReactionDTO(monsterAction.getName(), monsterAction.getDescription());
    }

    public LegendaryActionDTO buildLegendaryActionDTO(LegendaryAction legendaryAction) {
        return new LegendaryActionDTO(legendaryAction.getName(), legendaryAction.getDescription());
    }

    public ConditionDTO buildConditionDTO(Condition condition) {
        return new ConditionDTO(condition.getName(), condition.getDescription());
    }

    public CountMonstersDTO buildCountMonstersDTO(long amount) {
        return new CountMonstersDTO(amount);
    }


    public MonsterGetShortDTO buildMonsterGetShortDTO(Monster monster) {
        MonsterGetShortDTO.MonsterGetShortDTOBuilder builder = MonsterGetShortDTO.builder();
        builder.id(monster.getId());
        builder.name(monster.getMonsterName());
        builder.cr(monster.getCr());
        builder.imageUrl(monster.getImageUrl());
        return builder.build();
    }
}
