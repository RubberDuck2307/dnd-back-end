package dnd.monster_service.persistance.repository;

import dnd.monster_service.persistance.entity.creature.CreatureSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatureSizeRepository extends JpaRepository<CreatureSize, Long> {

    public Optional<CreatureSize> findBySize(String name);

}
