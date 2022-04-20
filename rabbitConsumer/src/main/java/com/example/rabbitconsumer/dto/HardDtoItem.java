package com.example.rabbitconsumer.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HardDtoItem implements Serializable {

    private String stringItem;

    private int intItem;

    @Override
    public String toString() {
        return "HardDtoItem{" +
                "stringItem='" + stringItem + '\'' +
                ", intItem=" + intItem +
                '}';
    }
}
