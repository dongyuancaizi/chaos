package com.cui.tech.chaos.model.login;

import lombok.Data;

/**
 * @author J.C
 * @date 2020/2/16 15:20
 */
@Data
public class WxBindPhoneDto  {
    private String wid;
    private String iv;
    private String encryptedData;
    private String session_key;
}
