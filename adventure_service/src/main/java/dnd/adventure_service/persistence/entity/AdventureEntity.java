package dnd.adventure_service.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "adventure")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdventureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(
            mappedBy = "adventure",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<GenericEntity> entities = new ArrayList<>();
}