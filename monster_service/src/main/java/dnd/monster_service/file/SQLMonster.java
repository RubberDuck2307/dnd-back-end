package dnd.monster_service.file;

import dnd.monster_service.persistance.model.creature.monster.LegendaryAction;
import dnd.monster_service.persistance.model.creature.monster.MonsterAction;
import dnd.monster_service.persistance.model.creature.monster.MonsterReaction;
import dnd.monster_service.persistance.model.creature.monster.MonsterTrait;
import dnd.monster_service.persistance.model.dice.DiceRoll;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class SQLMonster {
    private long id;
    private String name;
    private Set<String> types;
    private String size;
    private String armorClassDescription;
    private short armorClass;
    private short hitPoints;
    private DiceRoll hitDice;
    private Map<String, Short> speeds;
    private short strength;
    private short dexterity;
    private short constitution;
    private short intelligence;
    private short wisdom;
    private short charisma;
    private Short strengthSaveBonus;
    private Short dexteritySaveBonus;
    private Short constitutionSaveBonus;
    private Short intelligenceSaveBonus;
    private Short wisdomSaveBonus;
    private Short charismaSaveBonus;
    private Map<String, Short> skills;
    private short passivePerception;
    private Map<String, Short> senses;
    private List<String> damageImmunities;
    private List<String> damageResistances;
    private boolean notResistancesFromNonMagicalAttackFlag;
    private boolean notResistancesFromSilverAttackFlag;
    private boolean notResistancesFromAdamantineAttackFlag;
    private boolean notImmunitiesFromNonMagicalAttackFlag;
    private boolean notImmunitiesFromSilverAttackFlag;
    private boolean notImmunitiesFromAdamantineAttackFlag;
    private List<String> conditionImmunities;
    private List<String> damageVulnerabilities;
    private String cr;
    private List<MonsterTrait> traits;
    private List<MonsterAction> actions;
    private List<MonsterReaction> reactions;
    private List<LegendaryAction> legendaryActions;
    private String legendaryActionsDescription;
    private String imgUrl;
    private Set<String> languages;

    public SQLMonster() {

    }


}
