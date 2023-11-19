package dnd.encounter_service.view.monster;

import dnd.encounter_service.view.monster.entity.MonsterView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterViewWriterRepository extends JpaRepository<MonsterView, Long> {
}
