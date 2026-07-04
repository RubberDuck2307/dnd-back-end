package dnd.adventure_service.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "generic_entity")
@Getter
@Setter
@NoArgsConstructor
public class GenericEntity {

    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenericEntityType type;


}