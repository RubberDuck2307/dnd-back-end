package dnd.monster_service.persistance.repository;

import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterGroupRepository extends JpaRepository<MonsterGroup, Long> {

}
