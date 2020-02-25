package com.sike.online.classin.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginMessage {
    private String classid;
    private String userid;
}
