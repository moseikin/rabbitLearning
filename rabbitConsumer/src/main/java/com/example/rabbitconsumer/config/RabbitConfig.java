package com.example.rabbitconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// @PropertySource не поддерживает yaml файлы
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
    public Queue simpleMessageQueue() {
        return new Queue(simpleMessageQueueName, true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(simpleMessageQueue()).to(directExchange()).with(simpleMessageQueueName);
    }

    @Bean
    public Queue hardMessageQueue() {
        return new Queue(hardMessageQueueName, true, false, false);
    }

    @Bean
    public Binding bindingHard() {
        return BindingBuilder.bind(hardMessageQueue()).to(directExchange()).with(hardMessageQueueName);
    }
}
