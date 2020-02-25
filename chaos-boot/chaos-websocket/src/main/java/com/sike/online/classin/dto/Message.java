package com.sike.online.classin.dto;

import lombok.Data;

@Data
public class Message {
    private String uid;
    private String role;
    private String name;
    private String data;
    private String status;//0不在线 1 在线
}
