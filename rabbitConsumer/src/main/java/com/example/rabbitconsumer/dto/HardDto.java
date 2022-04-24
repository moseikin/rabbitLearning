package com.example.rabbitconsumer.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class HardDto implements Serializable {

    private String simpleString;

    private int simpleInt;

    private List<String> strings;

    private List<HardDtoItem> hardDtoItems;


    @Override
    public String toString() {
        return "HardDto{" +
                "simpleString='" + simpleString + '\'' +
                ", simpleInt=" + simpleInt +
                ", strings=" + strings +
                ", hardDtoItems=" + hardDtoItems +
                '}';
    }
}
