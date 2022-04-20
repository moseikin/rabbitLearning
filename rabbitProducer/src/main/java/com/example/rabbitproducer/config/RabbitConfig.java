package com.example.rabbitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value(value = "${spring.rabbitmq.exchangename}")
    private String exchangeName;

    @Value(value = "${spring.rabbitmq.queuename}")
    private String queueName;

    @Value(value = "${spring.rabbitmq.queuename-2}")
    private String queueName2;

    @Value(value = "${spring.rabbitmq.queuename-hard}")
    private String queueNameHard;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName, true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(queueName);
    }

    @Bean
    public Queue queue2() {
        return new Queue(queueName2, true, false, false);
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(directExchange()).with(queueName2);
    }

    @Bean
    public Queue queueNameHard() {
        return new Queue(queueNameHard, true, false, false);
    }

    @Bean
    public Binding bindingHard() {
        return BindingBuilder.bind(queueNameHard()).to(directExchange()).with(queueNameHard);
    }
}
