package dnd.encounter_service.config;

import dnd.encounter_service.model.service.interfaces.MonsterService;
import dnd.encounter_service.logic.encounter_creation.DefaultEncounterCreationLogic;
import dnd.encounter_service.logic.encounter_creation.EncounterCreationLogic;
import dnd.encounter_service.logic.encounter_difficulty.DifficultyService;
import dnd.encounter_service.view.monster.MonsterViewRepository;
import dnd.encounter_service.model.service.MonsterViewService;
import dnd.encounter_service.view.monster.entity.MonsterViewMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EncounterCreationLogic encounterCreationLogic(DifficultyService
                                                                 difficultyService) {
        return new DefaultEncounterCreationLogic(difficultyService);
    }

    @Bean
    public MonsterService monsterService(MonsterViewRepository monsterViewRepository, MonsterViewMapper MonsterViewMapper) {
        return new MonsterViewService(monsterViewRepository, MonsterViewMapper);
    }
}

