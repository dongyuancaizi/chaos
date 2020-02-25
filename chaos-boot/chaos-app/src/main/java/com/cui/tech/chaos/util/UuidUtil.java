package com.cui.tech.chaos.util;

import java.util.UUID;

/**
 * @author ZHI ZUN BAO
 * @since 2018/10/14
 */
public class UuidUtil {

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }

    /**
     * 时间+ UUID
     *
     * @return
     */
    public static String getId() {
        return (DateUtil.getCurrentDate() + getUuid());
    }

}
