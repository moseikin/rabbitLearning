package com.example.rabbitproducer.service;

import com.example.rabbitproducer.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final AmqpTemplate amqpTemplate;

    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        amqpTemplate.convertAndSend(messageDto.getExchange(), messageDto.getRoutingKey(), messageDto.getMessage());
        return messageDto;
    }
}
