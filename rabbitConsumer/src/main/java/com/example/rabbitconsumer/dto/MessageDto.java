package com.example.rabbitconsumer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDto {

    private String exchange;
    private String routingKey;
    private String message;
}
