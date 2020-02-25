package com.cui.tech.chaos.lite.exception;

import com.cui.tech.chaos.model.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defultExcepitonHandler(Exception e) {
        log.error("系统未知错误");
        e.printStackTrace();
        Result result = new Result();
        result.unknow();
        return result;
    }

    //声明要捕获的异常
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result businessException(HttpServletRequest request, BusinessException e) {
        Result result = e.getResult();
        log.warn(e.getObjectName() + "|" + e.getMethodName() + "=>" + e.getMessage());
        return result;
    }

}

