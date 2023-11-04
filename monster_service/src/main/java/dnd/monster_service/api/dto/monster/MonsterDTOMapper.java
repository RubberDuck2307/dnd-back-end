package dnd.monster_service.api.dto.monster;

import dnd.monster_service.api.dto.NameDescriptionEntityGetDTO;
import dnd.monster_service.api.dto.ability.AbilityScoreShortGetDTO;
import dnd.monster_service.api.dto.damage.DamageResistanceGetDTO;
import dnd.monster_service.api.dto.damage.DamageVulnerabilityGetDTO;
import dnd.monster_service.api.dto.dice.DiceRollGetDTO;
import dnd.monster_service.api.dto.sense.SenseGetDTO;
import dnd.monster_service.api.dto.skill.SkillGetDTO;
import dnd.monster_service.api.dto.speed.SpeedGetDTO;
import dnd.monster_service.persistance.model.creature.Condition;
import dnd.monster_service.persistance.model.creature.Language;
import dnd.monster_service.persistance.model.creature.monster.Monster;
import dnd.monster_service.persistance.model.creature.type.MonsterType;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MonsterDTOMapper {

    public MonsterDTOMapper() {
    }

    public MonsterFullGetDTO getMonsterFullGetDTO(Monster monster) {
        MonsterFullGetDTO.MonsterFullGetDTOBuilder builder = new MonsterFullGetDTO.MonsterFullGetDTOBuilder().
                monsterName(monster.getMonsterName())
                .cr(monster.getCr())
                .size(monster.getSize().getSize())
                .armorClass(monster.getArmorClass())
                .armorClassDescription(monster.getArmorClassDescription())
                .hitPoints(monster.getHitPoints())
                .passivePerception(monster.getPassivePerception())
                .imageUrl(monster.getImageUrl())
                .legendaryActionDescription(monster.getLegendaryActionDescription())
                .types(monster.getTypes().stream().map(MonsterType::getName).collect(java.util.stream.Collectors.toSet()))
                .skills(monster.getSkills().stream().map(skill -> new SkillGetDTO(skill.getSkill().getName(), skill.getBonus(),
                        skill.getSkill().getAbility().getTitle())).collect(
                        java.util.stream.Collectors.toSet()))
                .actions(monster.getActions().stream().map(action -> new NameDescriptionEntityGetDTO(
                        action.getDescription(), action.getName())).collect(Collectors.toSet()))
                .traits(monster.getTraits().stream().map(monsterTrait -> new NameDescriptionEntityGetDTO(
                        monsterTrait.getDescription(), monsterTrait.getTitle())).collect(
                        Collectors.toSet()))
                .monsterActions(monster.getActions().stream().map(monsterAction -> new NameDescriptionEntityGetDTO(
                        monsterAction.getDescription(), monsterAction.getName())).collect(
                        Collectors.toSet()))
                .legendaryActions(monster.getLegendaryActions().stream().map(monsterAction ->
                        new NameDescriptionEntityGetDTO(monsterAction.getDescription(), monsterAction.getName())).collect(
                        Collectors.toSet()))
                .reactions(monster.getReactions().stream().map(reaction -> new NameDescriptionEntityGetDTO(
                        reaction.getDescription(), reaction.getName())).collect(
                        Collectors.toSet()))
                .cr(monster.getCr())
                .hitDice(new DiceRollGetDTO(monster.getHitDice().getAmount(), monster.getHitDice().getDice(), monster.getHitDice()
                        .getConstant()))
                .monsterAbilityScores(monster.getMonsterAbilityScores().stream().map(
                        monsterAbilityScore -> new AbilityScoreShortGetDTO(monsterAbilityScore.getAbilityScore().getTitle(),
                                monsterAbilityScore.getValue())).collect(Collectors.toSet()))
                .savingThrows(monster.getSavingThrows().stream().map(monsterSavingThrow ->
                        new AbilityScoreShortGetDTO(monsterSavingThrow.getAbilityScore().getTitle(),
                                monsterSavingThrow.getValue())).collect(Collectors.toSet()))
                .speeds(monster.getSpeeds().stream().map(speedOfMonster -> new SpeedGetDTO(speedOfMonster.getSpeed()
                        .getName(), speedOfMonster.getValue())).collect(Collectors.toSet()))
                .senses(monster.getSenses().stream().map(senseOfMonster -> new SenseGetDTO(senseOfMonster.getSense()
                        .getName(), senseOfMonster.getRange())).collect(Collectors.toSet()))
                .languages(monster.getLanguages().stream().map(Language::getName).collect(Collectors.toSet()))
                .damageVulnerabilities(monster.getDamageVulnerabilities().stream().map(
                        damageVulnerability -> new DamageVulnerabilityGetDTO(damageVulnerability.getDamageType().getName(),
                                damageVulnerability.getAttackType() != null ? damageVulnerability.getAttackType().getName()
                                        : null)).collect(Collectors.toSet()))
                .damageResistances(monster.getDamageResistancesAndImmunities().stream().map(
                                damageResistance -> new DamageResistanceGetDTO(damageResistance.getDamageType().getName(),
                                        damageResistance.getAttackTypeException() != null ? damageResistance.
                                                getAttackTypeException().getName() : null, damageResistance.isImmune()))
                        .collect(Collectors.toSet()))
                .conditionImmunities(monster.getConditionImmunities().stream().map(Condition::getName).collect(
                        Collectors.toSet()));
        return builder.build();
    }


    public MonsterShortGetDTO getMonsterShortGetDTO(Monster monster) {
        return new MonsterShortGetDTO(monster.getId(), monster.getMonsterName(), monster.getCr());
    }

}
