package com.cui.tech.chaos.web;


import com.cui.tech.chaos.lite.helper.JWTHelper;
import com.cui.tech.chaos.lite.helper.RedisHelper;
import com.cui.tech.chaos.model.Constants;
import com.cui.tech.chaos.model.login.JwtData;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * 基础控制类，所有的Controller都规范继承此类，一些公用的代码后续可以在此扩展
 *
 * @version 1.0
 * @date 2018/10/12
 */
public abstract class BaseController<T> {
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private JWTHelper jwtHelper;

    public String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public String getLoginMU(HttpServletRequest request, String end) {
        String token = request.getHeader("token");
        String login_mu = "";
        if (StringUtils.isEmpty(token)) {
            return login_mu;
        }
        switch (end) {
            case Constants.MANAGE_END:
                JwtData jwtData = jwtHelper.getJwtData(token);
                login_mu = jwtData.getUser_mu();
                break;
            case Constants.WXMINI_END:
                login_mu = (String) redisHelper.hget(Constants.WXMINI_USER, token);
                break;
        }
        return login_mu;

    }

    public DataResult getResult(BindingResult bindingResult, boolean methodEnd) {
        DataResult dataResult = new DataResult();
        if (bindingResult.hasErrors()) {
            dataResult.failure("500", String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())));
            return dataResult;
        }
        boolean flag = methodEnd;
        if (!flag) {
            dataResult.failure();
        }
        dataResult.setData(methodEnd);
        return dataResult;
    }

    public DataResult getResult(boolean methodEnd) {
        DataResult dataResult = new DataResult();
        dataResult.setData(methodEnd);
        if (!methodEnd) {
            dataResult.failure();
        }
        return dataResult;
    }

    public DataResult getResult(BindingResult bindingResult, T methodReturn) {
        DataResult dataResult = new DataResult();
        if (bindingResult.hasErrors()) {
            dataResult.failure("500", String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())));
            return dataResult;
        }
        if (methodReturn == null) {
            dataResult.failure();
        }
        dataResult.setData(methodReturn);
        return dataResult;
    }

    public PageResult<T> getResult(PageList<T> pageList) {
        PageResult<T> listResult = new PageResult<T>();
        listResult.setPage(pageList);
        return listResult;
    }

    public DataResult<T> getResult(T data) {
        DataResult dataResult = new DataResult();
        dataResult.setData(data);
        return dataResult;
    }

}
