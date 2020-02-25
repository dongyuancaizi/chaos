package com.cui.tech.chaos.lite;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author J.C
 * @date 2020/2/21 16:35
 */
public class BaiduAddressUtils {
    @Autowired
    private RestTemplate restTemplate;

    public String getCity(String ip){
        String ak="C4SQOWPnOFY5VpbCs4v84kSYvASugnPH";
        String url = "https://api.map.baidu.com/location/ip?ak="+ak+"&ip="+ip+"&coor=bd09ll";
        JSONObject resp = restTemplate.getForObject(url, JSONObject.class);
        return null;
    }
}
