package com.cui.tech.chaos.lite.service;


import com.cui.tech.chaos.model.login.*;

public interface ILoginService {

    LoginUser doLogin(LoginDto loginDto);

    boolean doLogout(String mlu);

    String getInfoInServer(String data);

    default String refreshToken(String mu) {
        return "";
    }

}
