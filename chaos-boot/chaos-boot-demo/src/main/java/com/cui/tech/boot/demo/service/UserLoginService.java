package com.cui.tech.boot.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cui.tech.chaos.model.LoginDto;
import com.cui.tech.chaos.model.SessionUser;
import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.chaos.lite.service.impl.ManageLoginServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author Jian.cui
 * @date 2019/11/14 15:45
 */
@Component
@Service(interfaceClass = ILoginService.class)
public class UserLoginService extends ManageLoginServiceImpl {
    //@Autowired
    //private IMnSessionUserService iSessionUserService;
    @Override
    public SessionUser getUserInfo(LoginDto loginDto) {
        //return iSessionUserService.selectByUsernameAndPassword(loginDto);
        return null;
    }

}
