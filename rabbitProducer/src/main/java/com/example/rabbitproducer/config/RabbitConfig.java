package com.example.rabbitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:rabbit-queues.properties"})
public class RabbitConfig {

    @Value(value = "${exchange.direct-exchange}")
    private String directExchangeName;

    @Value(value = "${queue.simple-message-queue}")
    private String simpleMessageQueueName;

    @Value(value = "${queue.hard-message-queue}")
    private String hardMessageQueueName;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchangeName, true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(simpleMessageQueueName, true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(simpleMessageQueueName);
    }

    @Bean
    public Queue queueNameHard() {
        return new Queue(hardMessageQueueName, true, false, false);
    }

    @Bean
    public Binding bindingHard() {
        return BindingBuilder.bind(queueNameHard()).to(directExchange()).with(hardMessageQueueName);
    }

    @Bean
    public Map<String, String> queueNames() {
        Map<String, String> names = new HashMap<>();
        names.put("simple-message-queue", simpleMessageQueueName);
        names.put("hard-message-queue", hardMessageQueueName);
        return names;
    }
}
