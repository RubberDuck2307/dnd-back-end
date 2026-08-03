package dnd.adventure_service.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class LinkEntityId implements Serializable {

    @Column(name = "source", nullable = false)
    private UUID source;

    @Column(name = "target", nullable = false)
    private UUID target;

}