package com.cui.tech.chaos.model.result;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS("200", "成功"),
    REFRESH_TOKEN("201", "续签成功"),
    FAILURE("500", "失败"),
    UNKONW_ERROR("505", "未知错误"),
    LOGIN_AGAIN("401", "登录过期"),
    TOKEN_EXP("403", "Token过期");
    private String code;
    private String defaultMsg;

    ResultEnum(String code, String defaultMsg) {
        this.defaultMsg = defaultMsg;
        this.code = code;
    }


}
