package dnd.RestApi.game.damage;

import dnd.RestApi.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = SQLConfig.DAMAGE_TYPE_TABLE, schema = SQLConfig.SCHEMA)
public class DamageType {
    @GeneratedValue
    @Id
    private Long id;
    private String name;

}
