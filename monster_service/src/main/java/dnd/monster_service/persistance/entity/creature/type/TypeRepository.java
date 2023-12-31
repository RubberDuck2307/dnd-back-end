package dnd.monster_service.persistance.entity.creature.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<MonsterType, Long> {

    Optional<MonsterType> findByName(String name);

    Boolean existsByName(String name);
}
