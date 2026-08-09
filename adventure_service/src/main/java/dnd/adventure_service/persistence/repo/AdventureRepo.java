package dnd.adventure_service.persistence.repo;

import dnd.adventure_service.persistence.entity.AdventureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdventureRepo extends JpaRepository<AdventureEntity, UUID> {

}
