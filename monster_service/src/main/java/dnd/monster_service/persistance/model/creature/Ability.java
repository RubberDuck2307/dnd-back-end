package dnd.monster_service.persistance.model.creature;

import dnd.monster_service.config.SQLConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = SQLConfig.SCHEMA)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ability {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String abbreviation;
    private String description;

}
