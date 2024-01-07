package dnd.monster_service.persistance.entity.creature;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(schema = SQLConfig.SCHEMA)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ability {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String abbreviation;
    private String description;

}
