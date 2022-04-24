package com.example.rabbitproducer.service;

import com.example.rabbitproducer.dto.HardDto;
import com.example.rabbitproducer.dto.HardDtoItem;
import com.example.rabbitproducer.dto.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = {"classpath:rabbit-queues.properties"})
public class MessageServiceImpl implements MessageService {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    private final Map<String, String> queueNames;
    @Value(value = "${exchange.direct-exchange}")
    private String exchangeName;

    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        amqpTemplate.convertAndSend(exchangeName, queueNames.get("simple-message-queue"), messageDto.getMessage());
        log.info("Sent: " + messageDto.getMessage());
        return messageDto;
    }

    @Override
    public HardDto sendHardMessage(HardDto hardDto) {
        try {
            amqpTemplate.convertAndSend(exchangeName,
                    queueNames.get("hard-message-queue"),
                    objectMapper.writeValueAsString(hardDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return hardDto;
    }

    @Override
    public HardDto sendHardMessageCreatedWithCode() {
        HardDto hardDto = new HardDto();
        hardDto.setSimpleString("Строка из java-кода");
        hardDto.setSimpleInt(999);
        hardDto.setStrings(Arrays.asList("первая", "вторая", "третья"));
        HardDtoItem hardDtoItem1 = new HardDtoItem();
        hardDtoItem1.setIntItem(1);
        hardDtoItem1.setStringItem("item-1");
        HardDtoItem hardDtoItem2 = new HardDtoItem();
        hardDtoItem2.setIntItem(2);
        hardDtoItem2.setStringItem("item-2");
        hardDto.setHardDtoItems(Arrays.asList(hardDtoItem1, hardDtoItem2));

        try {
            amqpTemplate.convertAndSend(exchangeName,
                    queueNames.get("hard-message-queue"),
                    objectMapper.writeValueAsString(hardDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return hardDto;
    }
}


