package com.cui.tech.chaos.model.login;

import lombok.Data;

@Data
public class WxMiniLoginUser extends LoginUser {
    private Integer uid;
    private String token;
    private String username;
    private String phone;

}
