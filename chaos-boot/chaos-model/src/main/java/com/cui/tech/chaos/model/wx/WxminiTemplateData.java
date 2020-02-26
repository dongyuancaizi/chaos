package com.cui.tech.chaos.model.wx;

import lombok.Data;

/**
 * @author J.C
 * @date 2020/2/25 20:06
 */
@Data
public class WxminiTemplateData {
    private String value;

    public WxminiTemplateData(String value) {
        this.value = value;
    }
}
