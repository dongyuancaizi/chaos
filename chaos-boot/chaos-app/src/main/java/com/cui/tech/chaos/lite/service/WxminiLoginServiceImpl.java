package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.lite.JWTUtil;
import com.cui.tech.chaos.lite.RedisUtil;
import com.cui.tech.chaos.model.Constants;
import com.cui.tech.chaos.model.login.LoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public abstract class WxminiLoginServiceImpl implements ILoginService {

    @Autowired
    private RedisUtil redisUtil;

    public abstract WxMiniLoginUser getUserInfo(WxMiniLoginDto loginDto);

    /**
     * 登录处理
     *
     * @param loginDto
     * @return
     */
    @Override
    public WxMiniLoginUser doLogin(LoginDto loginDto) {
        WxMiniLoginDto wxMiniLoginDto = (WxMiniLoginDto) loginDto;
        WxMiniLoginUser user = getUserInfo(wxMiniLoginDto);
        if (user == null) {
            user = initUser(wxMiniLoginDto);
        } else {
            afterLogin(user);
        }
        user.setToken(wxMiniLoginDto.getToken());
        redisUtil.hset(Constants.WXMINI_USER, user.getToken(), user.getMu(), 1 * 24 * 60 * 60);
        return user;
    }

    protected abstract void afterLogin(WxMiniLoginUser user);

    protected abstract WxMiniLoginUser initUser(WxMiniLoginDto loginDto);

    @Override
    public String getInfoInServer(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return (String) redisUtil.hget(Constants.WXMINI_USER, token);
    }

    @Override
    public boolean doLogout(String token) {
        redisUtil.hdel(Constants.WXMINI_USER, token);
        return true;
    }

}
