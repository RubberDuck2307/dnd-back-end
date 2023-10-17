package dnd.RestApi.api.dtos.monster;

import dnd.RestApi.api.dtos.NameDescriptionEntityGetDTO;
import dnd.RestApi.api.dtos.ability.AbilityScoreShortGetDTO;
import dnd.RestApi.api.dtos.damage.DamageResistanceGetDTO;
import dnd.RestApi.api.dtos.damage.DamageVulnerabilityGetDTO;
import dnd.RestApi.api.dtos.dice.DiceRollGetDTO;
import dnd.RestApi.api.dtos.sense.SenseGetDTO;
import dnd.RestApi.api.dtos.skill.SkillGetDTO;
import dnd.RestApi.api.dtos.speed.SpeedGetDTO;
import dnd.RestApi.game.creature.Condition;
import dnd.RestApi.game.creature.Language;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.creature.monster.ability_score.MonsterSavingThrow;
import dnd.RestApi.game.creature.type.MonsterType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class MonsterFullGetDTO {

    private String monsterName;
    private String size;
    private short armorClass;
    private String armorClassDescription;
    private short hitPoints;
    private short passivePerception;
    private double cr;
    private Set<SkillGetDTO> skills;
    private String imageUrl;
    private String legendaryActionDescription;
    private Set<String> types;
    private Set<NameDescriptionEntityGetDTO> actions;
    private Set<NameDescriptionEntityGetDTO> traits;
    private Set<NameDescriptionEntityGetDTO> monsterActions;
    private Set<NameDescriptionEntityGetDTO> legendaryActions;
    private Set<NameDescriptionEntityGetDTO> reactions;
    private DiceRollGetDTO hitDice;
    private Set<AbilityScoreShortGetDTO> monsterAbilityScores;
    private Set<AbilityScoreShortGetDTO> savingThrows;
    private Set<SpeedGetDTO> speeds;
    private Set<SenseGetDTO> senses;
    private Set<String> languages;
    private Set<DamageVulnerabilityGetDTO> damageVulnerabilities;
    private Set<DamageResistanceGetDTO> damageResistances;
    private Set<String> conditionImmunities;

    public MonsterFullGetDTO(Monster monster) {
        this.monsterName = monster.getMonsterName();
        this.size = monster.getSize().getSize();
        this.armorClass = monster.getArmorClass();
        this.armorClassDescription = monster.getArmorClassDescription();
        this.hitPoints = monster.getHitPoints();
        this.passivePerception = monster.getPassivePerception();
        this.imageUrl = monster.getImageUrl();
        this.legendaryActionDescription = monster.getLegendaryActionDescription();
        this.types = monster.getTypes().stream().map(MonsterType::getName).collect(java.util.stream.Collectors.toSet());
        this.skills = monster.getSkills().stream().map(SkillGetDTO::new).collect(java.util.stream.Collectors.toSet());
        this.actions = monster.getActions().stream().map(action -> new NameDescriptionEntityGetDTO(
                action.getDescription(), action.getName())).collect(Collectors.toSet());
        this.traits = monster.getTraits().stream().map(monsterTrait -> new NameDescriptionEntityGetDTO(
                monsterTrait.getDescription(), monsterTrait.getTitle())).collect(
                Collectors.toSet());
        this.monsterActions = monster.getActions().stream().map(monsterAction -> new NameDescriptionEntityGetDTO(
                monsterAction.getDescription(), monsterAction.getName())).collect(
                Collectors.toSet());
        this.legendaryActions = monster.getLegendaryActions().stream().map(monsterAction ->
                new NameDescriptionEntityGetDTO(monsterAction.getDescription(), monsterAction.getName())).collect(
                Collectors.toSet());
        this.reactions = monster.getReactions().stream().map(reaction -> new NameDescriptionEntityGetDTO(
                reaction.getDescription(), reaction.getName())).collect(
                Collectors.toSet());
        this.cr = monster.getCr();
        this.hitDice = new DiceRollGetDTO(monster.getHitDice());
        this.monsterAbilityScores = monster.getMonsterAbilityScores().stream().map(
                monsterAbilityScore -> new AbilityScoreShortGetDTO(monsterAbilityScore.getAbilityScore().getTitle(),
                        monsterAbilityScore.getValue())).collect(Collectors.toSet());
        this.savingThrows = monster.getSavingThrows().stream().map(monsterSavingThrow ->
                new AbilityScoreShortGetDTO(monsterSavingThrow.getAbilityScore().getTitle(),
                        monsterSavingThrow.getValue())).collect(Collectors.toSet());
        this.speeds = monster.getSpeeds().stream().map(speedOfMonster -> new SpeedGetDTO(speedOfMonster.getSpeed()
                .getName(), speedOfMonster.getValue())).collect(Collectors.toSet());
        this.senses = monster.getSenses().stream().map(senseOfMonster -> new SenseGetDTO(senseOfMonster.getSense()
                .getName(), senseOfMonster.getRange())).collect(Collectors.toSet());
        this.languages = monster.getLanguages().stream().map(Language::getName).collect(Collectors.toSet());
        this.damageVulnerabilities = monster.getDamageVulnerabilities().stream().map(
                damageVulnerability -> new DamageVulnerabilityGetDTO(damageVulnerability.getDamageType().getName(),
                        damageVulnerability.getAttackType() != null ? damageVulnerability.getAttackType().getName()
                                : null)).collect(Collectors.toSet());
        this.damageResistances = monster.getDamageResistancesAndImmunities().stream().map(
                        damageResistance -> new DamageResistanceGetDTO(damageResistance.getDamageType().getName(),
                                damageResistance.getAttackTypeException() != null ? damageResistance.
                                        getAttackTypeException().getName() : null, damageResistance.isImmune()))
                .collect(Collectors.toSet());
        this.conditionImmunities = monster.getConditionImmunities().stream().map(Condition::getName).collect(
                Collectors.toSet());
    }
}
