package com.cui.tech.boot.demo.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cui.tech.chaos.annotation.WxminiLoginToken;
import com.cui.tech.chaos.model.Result;
import com.cui.tech.chaos.model.LoginDto;
import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.chaos.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/web")
@Api(tags = "LoginController", description = "登录模块")
public class WebLoginController extends BaseController {

    @Reference
    private ILoginService iLoginService;

    /**
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public Result login(@RequestBody  LoginDto user, BindingResult bindingResult) {
        //log.info("用户[{}]登录", user.getUsername());
        return getResult(bindingResult,iLoginService.doLogin(user));
    }
    @PostMapping("/logout")
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public Result logout(@RequestBody  LoginDto user, BindingResult bindingResult) {
        //log.info("用户[{}]登录", user.getUsername());
        return getResult(bindingResult,iLoginService.doLogin(user));
    }

    @WxminiLoginToken
    @GetMapping("/getMessage")
    @ApiOperation(value = "获取信息", notes = "", httpMethod = "GET")
    public String getMessage() {
        return "你已通过验证";
    }


}
