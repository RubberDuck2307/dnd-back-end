package dnd.encounter_service.view.monster.entity;

import dnd.encounter_service.view.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterViewRepository extends ReadOnlyRepository<Monster, Long> {

}
