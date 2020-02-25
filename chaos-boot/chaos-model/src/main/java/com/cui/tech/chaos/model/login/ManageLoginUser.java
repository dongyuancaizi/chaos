package com.cui.tech.chaos.model.login;

import lombok.Data;

@Data
public class ManageLoginUser extends LoginUser {
    private String token;
    private String username;
    private String phone;

}
