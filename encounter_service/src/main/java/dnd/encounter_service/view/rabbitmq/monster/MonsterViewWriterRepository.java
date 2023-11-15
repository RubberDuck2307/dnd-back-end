package dnd.encounter_service.view.rabbitmq.monster;

import dnd.encounter_service.view.monster.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterViewWriterRepository extends JpaRepository<Monster, Long> {
}
