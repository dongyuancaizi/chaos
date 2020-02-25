package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.lite.JWTUtil;
import com.cui.tech.chaos.lite.RedisUtil;
import com.cui.tech.chaos.model.Constants;
import com.cui.tech.chaos.model.login.JwtData;
import com.cui.tech.chaos.model.login.LoginDto;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ManageLoginServiceImpl implements ILoginService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JWTUtil jwtUtil;

    public abstract ManageLoginUser getUserInfo(ManageLoginDto loginDto);

    /**
     * 登录处理
     *
     * @param loginDto
     * @return
     */
    @Override
    public ManageLoginUser doLogin(LoginDto loginDto) {
        ManageLoginUser user = getUserInfo((ManageLoginDto) loginDto);
        if (user == null) {
            return null;
        }
        String token = jwtUtil.createToken(user.getMu(), user.getUsername());
        user.setToken(token);
        redisUtil.hset(Constants.MANAGE_USER, user.getMu(), user, 30 * 24 * 60 * 60);
        return user;
    }

    @Override
    public String getInfoInServer(String mu) {
        ManageLoginUser user = (ManageLoginUser) redisUtil.hget(Constants.MANAGE_USER, mu);
        if (user == null) return null;
        return user.getMu();
    }

    @Override
    public String refreshToken(String mu) {
        ManageLoginUser user = (ManageLoginUser) redisUtil.hget(Constants.MANAGE_USER, mu);
        if (user == null) {
            return null;
        }
        return jwtUtil.createToken(user.getMu(), user.getUsername());
    }

    @Override
    public boolean doLogout(String mu) {
        redisUtil.hdel(Constants.MANAGE_USER, mu);
        return true;
    }


}
