package com.cui.tech.chaos.model.login;

import lombok.Data;

/**
 * @author J.C
 * @date 2020/2/14 16:34
 */
@Data
public class WxMiniLoginDto extends LoginDto {
    private String code;
    private String openid;
    private String ip;
    private String token;
}
