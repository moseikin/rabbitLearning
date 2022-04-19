package com.example.rabbitproducer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDto {

    /**
     * "directexchange"
     */
    private String exchange;
    /**
     * "queuename" или "queuename-2"
     */
    private String routingKey;
    private String message;
}
