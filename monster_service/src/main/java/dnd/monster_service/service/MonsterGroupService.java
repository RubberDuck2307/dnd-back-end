package dnd.monster_service.service;

import dnd.monster_service.kafka.monster_group.MonsterGroupKafkaProducer;
import dnd.monster_service.persistance.entity.creature.monster.Monster;
import dnd.monster_service.persistance.entity.creature.monster.MonsterGroup;
import dnd.monster_service.persistance.repository.MonsterGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MonsterGroupService {

    private final MonsterGroupRepository repository;
    private final MonsterService monsterService;
    private final MonsterGroupKafkaProducer kafkaProducer;
    public void createMonsterGroup(List<Long> monsterIds, String name) {
        List<Monster> monsters = monsterService.getMonstersByIds(monsterIds);
        MonsterGroup monsterGroup = new MonsterGroup(name, new HashSet<>(monsters));
        monsters.forEach(monster -> monster.addMonsterGroup(monsterGroup));
        MonsterGroup saved = repository.save(monsterGroup);
        kafkaProducer.sendNewMonsterGroupMessage(saved);
    }

    @Transactional
    public void updateMonsterGroup(List<Long> addedMonsters, List<Long> removedMonsters, Long monsterGroupId){
       var monsterGroup = repository.findById(monsterGroupId).orElseThrow();
       if (removedMonsters != null && !removedMonsters.isEmpty()){
           removedMonsters.forEach(monsterGroup::removeMonster);
       }
       addedMonsters.forEach(id -> {
         var monster = monsterService.getMonsterById(id);
         monsterGroup.addMonster(monster);
         monster.addMonsterGroup(monsterGroup);
       });
       kafkaProducer.sendMonsterGroupEditedMessage(monsterGroup);
    }
}
