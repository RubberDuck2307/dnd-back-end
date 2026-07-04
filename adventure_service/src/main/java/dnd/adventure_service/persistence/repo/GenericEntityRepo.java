package dnd.adventure_service.persistence.repo;

import dnd.adventure_service.persistence.entity.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenericEntityRepo extends JpaRepository<GenericEntity, UUID> {
}
