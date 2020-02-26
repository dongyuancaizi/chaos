package com.cui.tech.chaos.model.wx;

import lombok.Data;

import java.util.Map;

/**
 * @author J.C
 * @date 2020/2/25 20:02
 */
@Data
public class WxminiMessage {
    //用户openid
    private String touser;
    //订阅消息模版id
    private String template_id;
    //默认跳到小程序首页
    private String page = "pages/index/index";
    //推送文字
    private Map<String, WxminiTemplateData> data;
}
