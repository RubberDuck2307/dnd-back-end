package dnd.rest_api.api.dto.monster;

import dnd.rest_api.api.dto.NameDescriptionEntityGetDTO;
import dnd.rest_api.api.dto.ability.AbilityScoreShortGetDTO;
import dnd.rest_api.api.dto.damage.DamageResistanceGetDTO;
import dnd.rest_api.api.dto.damage.DamageVulnerabilityGetDTO;
import dnd.rest_api.api.dto.dice.DiceRollGetDTO;
import dnd.rest_api.api.dto.sense.SenseGetDTO;
import dnd.rest_api.api.dto.skill.SkillGetDTO;
import dnd.rest_api.api.dto.speed.SpeedGetDTO;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
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
}
