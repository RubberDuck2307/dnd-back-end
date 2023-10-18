package dnd.aiapi.config;

import dnd.aiapi.client.AiClient;
import dnd.aiapi.client.OpenAiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public AiClient aiClient(){
        return new OpenAiClient(restTemplate());
    }
}
