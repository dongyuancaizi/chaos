package com.cui.tech.chaos.lite.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/manage")
@Api(tags = "ManageLoginController", description = "登录模块")
public class ManageLoginController extends BaseController {

    @Reference(group = "mn")
    private ILoginService iLoginService;

    /**
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<ManageLoginUser> login(@RequestBody ManageLoginDto user, BindingResult bindingResult) {
        log.info("用户[{}]登录", user.getUsername());
        return getResult(bindingResult, (ManageLoginUser)iLoginService.doLogin(user));
    }
//    @PostMapping("/logout")
//    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
//    public Result logout(@RequestBody LoginDto user, BindingResult bindingResult) {
//        log.info("用户[{}]登出", user.getUsername());
//        return getResult(bindingResult,iLoginService.doLogout(user));
//    }


}
