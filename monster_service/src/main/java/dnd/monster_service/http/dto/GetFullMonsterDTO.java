package dnd.monster_service.http.dto;

import dnd.dto.ConditionDTO;
import dnd.dto.DiceRollDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class GetFullMonsterDTO {
    private long id;
    private Float cr;
    private String monsterName;
    private Short passivePerception;
    private String imageUrl;
    private DiceRollDTO hitDice;
    private Short hitPoints;
    private Boolean homebrew;
    private String description;
    private String size;
    private Short armorClass;
    private String armorClassDescription;
    private String legendaryActionDescription;
    private Set<MonsterTypeDTO> types;
    private Set<MonsterAbilityScoreDTO> monsterAbilityScores;
    private Set<MonsterSpeedDTO> speeds;
    private Set<MonsterSkillsDTO> skills;
    private Set<MonsterSenseDTO> senses;
    private Set<MonsterTraitDTO> traits;
    private Set<MonsterActionDTO> actions;
    private Set<MonsterReactionDTO> reactions;
    private Set<LegendaryActionDTO> legendaryActions;
    private Set<MonsterDamageDTO> damageResistancesAndImmunities;
    private Set<MonsterVulnerabilityDTO> damageVulnerabilities;
    private Set<ConditionDTO> conditionImmunities;
}

