package com.cui.tech.chaos.lite.service;


import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;

/**
 * @author Jian.cui
 * @date 2019/11/15 15:10
 */
public interface IWxLoginUserService {

    WxMiniLoginUser selectByWXminiOpenid(WxMiniLoginDto loginDto);
}
