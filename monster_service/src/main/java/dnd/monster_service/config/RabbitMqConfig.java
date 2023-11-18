package dnd.monster_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String TOPIC_EXCHANGE_NAME = "monster";
    public static final String MONSTER_CREATED_QUEUE = "monster";
    public static final String MONSTER_ROUTING_KEY = "monster";

    @Bean
    public Queue queue() {
        return new Queue(MONSTER_CREATED_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("monster");
    }


}
