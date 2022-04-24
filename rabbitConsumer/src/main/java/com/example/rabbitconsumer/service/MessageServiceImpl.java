package com.example.rabbitconsumer.service;

import com.example.rabbitconsumer.dto.HardDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = {"classpath:rabbit-queues.properties"})
public class MessageServiceImpl implements MessageService {

    private final ObjectMapper objectMapper;

    @RabbitHandler
    @RabbitListener(queues = "${queue.simple-message-queue}")
    @Override
    public void receiveMessage(@Payload String message) {
        log.info("Message from queue: " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "${queue.hard-message-queue}")
    @Override
    public void receiveHardMessage(@Payload String s) {
        try {
            HardDto hardDto = objectMapper.readValue(s, HardDto.class);
            log.info("received hard: " + hardDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
