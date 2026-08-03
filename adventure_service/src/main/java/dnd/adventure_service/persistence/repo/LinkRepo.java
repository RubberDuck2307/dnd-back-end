package dnd.adventure_service.persistence.repo;

import dnd.adventure_service.persistence.entity.LinkEntity;
import dnd.adventure_service.persistence.entity.LinkEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepo extends JpaRepository<LinkEntity, LinkEntityId> {
}
