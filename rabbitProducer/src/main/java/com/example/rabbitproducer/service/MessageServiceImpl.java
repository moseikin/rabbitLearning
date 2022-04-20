package com.example.rabbitproducer.service;

import com.example.rabbitproducer.dto.HardDto;
import com.example.rabbitproducer.dto.HardDtoItem;
import com.example.rabbitproducer.dto.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        amqpTemplate.convertAndSend(messageDto.getExchange(), messageDto.getRoutingKey(), messageDto.getMessage());
        return messageDto;
    }

    @Override
    public HardDto sendHardMessage(HardDto hardDto) {
        try {
            amqpTemplate.convertAndSend(hardDto.getExchange(), hardDto.getRoutingKey(), objectMapper.writeValueAsString(hardDto));
            return hardDto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HardDto sendHardMessageCreatedWithCode() {
        HardDto hardDto = new HardDto();
        hardDto.setExchange("directexchange");
        hardDto.setRoutingKey("queuename-hard");
        hardDto.setSimpleString("Строка из java-кода");
        hardDto.setSimpleInt(999);
        hardDto.setStrings(Arrays.asList("первая", "вторая", "третья"));
        HardDtoItem hardDtoItem1 = new HardDtoItem();
        hardDtoItem1.setIntItem(1);
        hardDtoItem1.setStringItem("Итем1");
        HardDtoItem hardDtoItem2 = new HardDtoItem();
        hardDtoItem2.setIntItem(2);
        hardDtoItem2.setStringItem("Итем2");
        hardDto.setHardDtoItems(Arrays.asList(hardDtoItem1, hardDtoItem2));

        try {
            amqpTemplate.convertAndSend(hardDto.getExchange(), hardDto.getRoutingKey(), objectMapper.writeValueAsString(hardDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return hardDto;
    }
}


