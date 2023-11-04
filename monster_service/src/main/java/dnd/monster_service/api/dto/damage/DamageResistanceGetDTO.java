package dnd.monster_service.api.dto.damage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DamageResistanceGetDTO {

    private String DamageType;

    private String AttackExceptionType;

    private boolean isImmune;
}
