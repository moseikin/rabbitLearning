package com.example.rabbitproducer.controller;

import com.example.rabbitproducer.dto.MessageDto;
import com.example.rabbitproducer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(path = "/message")
    public MessageDto postMessage(@RequestBody MessageDto messageDto) {
        return messageService.sendMessage(messageDto);
    }

}