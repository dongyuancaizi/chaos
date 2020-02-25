package com.sike.online.classin.dto;

import lombok.Data;

@Data
public class UpdateMessage {
    private String fromuid ;
    private String[] touid ;
    private String data;
}
