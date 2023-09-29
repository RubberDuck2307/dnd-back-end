package dnd.RestApi.config;


import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dnd.RestApi.game.creature.monster.MonsterNativeQueries;
import dnd.RestApi.game.creature.monster.MonsterRepository;
import dnd.RestApi.game.encounter.encounter_creating.DefaultEncounterCreationLogic;
import dnd.RestApi.game.encounter.encounter_creating.EncounterCreationLogic;
import dnd.RestApi.game.encounter.encounter_difficulty.EncounterDifficultyMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {



    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS).build();
    }

    @Bean
    public EncounterCreationLogic encounterCreationLogic(MonsterRepository monsterRepository, EncounterDifficultyMap
            encounterDifficultyMap, MonsterNativeQueries monsterNativeQueries) {
        return new DefaultEncounterCreationLogic(encounterDifficultyMap, monsterRepository, monsterNativeQueries);
    }


}
