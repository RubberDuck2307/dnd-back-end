package dnd.adventure_service.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "generic_entity",
        indexes = {
                @Index(name = "idx_entity_name", columnList = "name")
        })
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adventure_id")
    private AdventureEntity adventure;

    @OneToMany(mappedBy = "source")
    private Set<LinkEntity> outgoingLinks = new HashSet<>();

    @OneToMany(mappedBy = "target")
    private Set<LinkEntity> incomingLinks = new HashSet<>();
}