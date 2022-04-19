package com.example.rabbitconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService{

    @RabbitHandler
    @RabbitListener(queues = "${spring.rabbitmq.queuename}")
    @Override
    public void receiveMessage(@Payload String message) {
        log.info("Message from queue: " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "${spring.rabbitmq.queuename-2}")
    @Override
    public void receiveMessageQueue2(@Payload String message) {
        log.info("Message from queue2: " + message);
    }
}
