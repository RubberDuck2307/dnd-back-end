package dnd.RestApi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * This class specify paths to files.
 */

@Configuration
@RequiredArgsConstructor
public class FileConfig {


    @Bean(name = "encounterDifficultyFile")
    public File encounterDifficultyFile() {
        return new File("src/main/resources/encounter_difficulty.json");
    }

    @Bean(name = "monstersFile")
    public File monstersFile() {
        return new File("src/main/resources/monsters.json");
    }
}
