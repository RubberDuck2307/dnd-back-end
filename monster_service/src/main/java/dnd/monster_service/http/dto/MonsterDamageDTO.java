package dnd.monster_service.http.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class MonsterDamageDTO {

    private String damageType;
    private String attackTypeException;
    private boolean isImmune;
    private final boolean isResistant = true;

    public MonsterDamageDTO(String damageType, String attackTypeException, boolean isImmune) {
        this.damageType = damageType;
        this.attackTypeException = attackTypeException;
        this.isImmune = isImmune;
    }
}
