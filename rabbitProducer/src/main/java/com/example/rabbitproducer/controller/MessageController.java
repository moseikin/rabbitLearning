package com.example.rabbitproducer.controller;

import com.example.rabbitproducer.dto.HardDto;
import com.example.rabbitproducer.dto.MessageDto;
import com.example.rabbitproducer.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @GetMapping(path = "/")
    public String hello() {
        return "hello";
    }

    @PostMapping(path = "/message")
    public MessageDto postMessage(@RequestBody MessageDto messageDto) {
        log.info("INTO THE CONTROLLER");
        return messageService.sendMessage(messageDto);
    }

    @PostMapping(path = "/message-hard")
    public HardDto postHardMessage(@RequestBody HardDto hardDto) {
        return messageService.sendHardMessage(hardDto);
    }

    @PostMapping(path = "/message-hard-code")
    public HardDto postHardCodeMessage() {
        return messageService.sendHardMessageCreatedWithCode();
    }

}
