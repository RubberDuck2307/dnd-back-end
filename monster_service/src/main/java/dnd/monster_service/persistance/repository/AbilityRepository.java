package dnd.monster_service.persistance.repository;

import dnd.monster_service.persistance.entity.creature.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Long> {

    Optional<Ability> findByTitle(String title);
}
