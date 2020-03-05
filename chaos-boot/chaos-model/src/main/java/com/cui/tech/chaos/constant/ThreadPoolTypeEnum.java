package com.cui.tech.chaos.constant;

/**
 * 线程池类型的枚举类
 *
 * @author sangweidong
 * @create 2019-04-18 17:02
 **/
public enum ThreadPoolTypeEnum {
    IO_INTENSIVE(1, "IO密集型"),
    CPU_INTENSIVE(2, "CPU密集型");

    public Integer CODE;
    public String TYPE;

    ThreadPoolTypeEnum(Integer code, String type) {
        this.CODE = code;
        this.TYPE = type;
    }

    public static ThreadPoolTypeEnum get(Integer code) {
        ThreadPoolTypeEnum result = null;
        if (code != null) {
            for (ThreadPoolTypeEnum value : ThreadPoolTypeEnum.values()) {
                if (code == value.CODE) {
                    result = value;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean contain(Integer code) {
        boolean result = false;
        if (code != null) {
            for (ThreadPoolTypeEnum value : ThreadPoolTypeEnum.values()) {
                if (code == value.CODE) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
