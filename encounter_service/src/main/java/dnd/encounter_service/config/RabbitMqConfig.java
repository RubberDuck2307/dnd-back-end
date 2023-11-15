package dnd.encounter_service.config;

import dnd.encounter_service.view.rabbitmq.monster.MonsterViewConsumerService;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class RabbitMqConfig {

    public static final String monsterCreatedQueue = "monsterCreated";

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

}
