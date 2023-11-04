package dnd.monster_service.api.dto.monster;

import dnd.monster_service.api.dto.NameDescriptionEntityGetDTO;
import dnd.monster_service.api.dto.ability.AbilityScoreShortGetDTO;
import dnd.monster_service.api.dto.damage.DamageResistanceGetDTO;
import dnd.monster_service.api.dto.damage.DamageVulnerabilityGetDTO;
import dnd.monster_service.api.dto.dice.DiceRollGetDTO;
import dnd.monster_service.api.dto.sense.SenseGetDTO;
import dnd.monster_service.api.dto.skill.SkillGetDTO;
import dnd.monster_service.api.dto.speed.SpeedGetDTO;
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
