package com.example.rabbitproducer.service;

import com.example.rabbitproducer.dto.HardDto;
import com.example.rabbitproducer.dto.MessageDto;

public interface MessageService {

    MessageDto sendMessage(MessageDto messageDto);

    HardDto sendHardMessage(HardDto hardDto);

    HardDto sendHardMessageCreatedWithCode();
}
