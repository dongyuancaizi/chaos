package com.cui.tech.chaos.lite.exception;

import com.cui.tech.chaos.model.result.Result;
import com.cui.tech.chaos.model.result.ResultEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    //出错的对象类
    private String ObjectName = "unknow";
    //出错的对象方法
    private String methodName = "unknow";
    private Result result;

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getDefaultMsg());
        this.result = new Result(resultEnum.getCode(), resultEnum.getDefaultMsg());
    }

    public BusinessException(ResultEnum resultEnum, String msg) {
        super(msg);
        this.result = new Result(resultEnum.getCode(), msg);
    }

    public BusinessException(String ObjectName, String methodName, ResultEnum resultEnum) {
        super(resultEnum.getDefaultMsg());
        this.ObjectName = ObjectName;
        this.methodName = methodName;
        this.result = new Result(resultEnum.getCode(), resultEnum.getDefaultMsg());
    }

    public BusinessException(String ObjectName, String methodName, ResultEnum resultEnum, String msg) {
        super(msg);
        this.ObjectName = ObjectName;
        this.methodName = methodName;
        this.result = new Result(resultEnum.getCode(), msg);
    }
}
