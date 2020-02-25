package com.sike.online.classin.dto;

import lombok.Data;

@Data
public class NoticeMessage {
    private String fromuid;
    private String[] touid ;
    private String data;
}
