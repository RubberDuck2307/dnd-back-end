package dnd.adventure_service.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entity_relationship")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity {

    @EmbeddedId
    private LinkEntityId id;

    @MapsId("source")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source", nullable = false)
    private GenericEntity source;

    @MapsId("target")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target", nullable = false)
    private GenericEntity target;

    @Column(name = "bidirectional", nullable = false)
    private boolean bidirectional;

    @Column(name = "relationship", nullable = false, length = 100)
    private String relationship;
}