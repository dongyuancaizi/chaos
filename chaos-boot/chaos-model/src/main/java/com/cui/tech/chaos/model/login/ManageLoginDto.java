package com.cui.tech.chaos.model.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManageLoginDto extends LoginDto {

    @NotBlank(message = "账户不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

}
