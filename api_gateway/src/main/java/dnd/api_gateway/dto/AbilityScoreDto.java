package dnd.api_gateway.dto;

import lombok.Data;

@Data
public class AbilityScoreDto {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
}
