package com.cui.tech.chaos.lite.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cui.tech.chaos.annotation.ManageLoginToken;
import com.cui.tech.chaos.annotation.WxminiLoginToken;
import com.cui.tech.chaos.lite.helper.JWTHelper;
import com.cui.tech.chaos.lite.exception.BusinessException;
import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.chaos.model.login.JwtData;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.ResultEnum;
import com.cui.tech.chaos.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Reference(group = "mn")
    ILoginService mnLoginService;
    @Reference(group = "wx")
    ILoginService wxLoginService;
    @Autowired
    private JWTHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(ManageLoginToken.class)) {
            ManageLoginToken manageLoginToken = method.getAnnotation(ManageLoginToken.class);
            if (manageLoginToken.required()) {
                String token = httpServletRequest.getHeader("token");
                return handleManageLogin(token);
            }
        } else if (method.isAnnotationPresent(WxminiLoginToken.class)) {
            WxminiLoginToken wxminiLoginToken = method.getAnnotation(WxminiLoginToken.class);
            if (wxminiLoginToken.required()) {
                String token = httpServletRequest.getHeader("token");
                return handleWxminiLogin(token);
            }
        }
        return true;
    }

    private boolean handleWxminiLogin(String token) {
        // 执行认证
        if (token == null) {
            throw new BusinessException(ResultEnum.LOGIN_AGAIN, "无token，请重新登录");
        }
        String wid = wxLoginService.getInfoInServer(token);
        if (StringUtils.isEmpty(wid)) {
            throw new BusinessException(ResultEnum.LOGIN_AGAIN, "用户不存在，请重新登录");
        }
        return true;
    }

    private boolean handleManageLogin(String token) {
        // 执行认证
        if (token == null) {
            throw new BusinessException(ResultEnum.LOGIN_AGAIN, "无token，请重新登录");
        }
        JwtData jwtData = jwtHelper.getJwtData(token);
        if (jwtData.getUser_mu() == null) {
            throw new BusinessException(ResultEnum.LOGIN_AGAIN, "无效token，请重新登录");
        }
        String session_user_mu = mnLoginService.getInfoInServer(jwtData.getUser_mu());
        if (StringUtils.isEmpty(session_user_mu)) {
            throw new BusinessException(ResultEnum.LOGIN_AGAIN, "用户不存在，请重新登录");
        }
        if (DateUtil.compareDate(jwtData.getExp(), new Date()) == -1) {
            String refresh = mnLoginService.refreshToken(jwtData.getUser_mu());
            if (StringUtils.isEmpty(refresh)) {
                throw new BusinessException(ResultEnum.LOGIN_AGAIN, "登录失效，请重新登录");
            }
            throw new BusinessException(ResultEnum.REFRESH_TOKEN, refresh);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void buildResponse(HttpServletResponse response, String code, String msg) {
        DataResult dataResultDto = new DataResult();
        dataResultDto.failure(code, msg);
        response.addHeader("sessionstatus", "timeOut");
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getOutputStream().write(JSONObject.toJSONBytes(dataResultDto, SerializerFeature.QuoteFieldNames));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
