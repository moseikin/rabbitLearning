package com.example.rabbitconsumer.service;

public interface MessageService {

    void receiveMessage(String message);

    void receiveHardMessage(String s);
}
