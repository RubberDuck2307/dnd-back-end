package dnd.RestApi.api.dtos.monster;

import dnd.RestApi.api.dtos.NameDescriptionEntityGetDTO;
import dnd.RestApi.api.dtos.skill.SkillGetDTO;
import dnd.RestApi.game.creature.monster.Monster;
import dnd.RestApi.game.creature.type.MonsterType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public class MonsterFullGetDTO {

    private String name;
    private String size;
    private short armorClass;
    private String armorClassDescription;
    private short hitPoints;
    private short passivePerception;
    private Set<SkillGetDTO> skills;
    private String imageUrl;
    private String legendaryActionDescription;
    private Set<String> types;
    private Set<NameDescriptionEntityGetDTO> actions;
    private Set<NameDescriptionEntityGetDTO> traits;
    private Set<NameDescriptionEntityGetDTO> monsterActions;
    private Set<NameDescriptionEntityGetDTO> legendaryActions;
    private Set<NameDescriptionEntityGetDTO> reactions;

    public MonsterFullGetDTO(Monster monster) {
        this.name = monster.getMonsterName();
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

    }
}
