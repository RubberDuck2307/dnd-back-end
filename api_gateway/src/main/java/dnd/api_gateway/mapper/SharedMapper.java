package dnd.api_gateway.mapper;

import dnd.api_gateway.dto.AbilityScoreDto;
import dnd.generated.AbilityOuterClass;
import dnd.generated.AbilityScoreOuterClass;
@Deprecated //TODO CHANGE THIS MAPPING
public class SharedMapper {

    public AbilityOuterClass.Ability buildAbility(String name, int score) {
        return AbilityOuterClass.Ability.newBuilder()
                .setName(name)
                .setValue(score)
                .build();
    }

    public AbilityScoreOuterClass.AbilityScore buildAbilityScore(AbilityScoreDto dto) {
        return AbilityScoreOuterClass.AbilityScore.newBuilder()
                .addAbilities(buildAbility("strength", dto.getStrength()))
                .addAbilities(buildAbility("dexterity", dto.getDexterity()))
                .addAbilities(buildAbility("constitution", dto.getConstitution()))
                .addAbilities(buildAbility("intelligence", dto.getIntelligence()))
                .addAbilities(buildAbility("wisdom", dto.getWisdom()))
                .addAbilities(buildAbility("charisma", dto.getCharisma()))
                .build();
    }

}
