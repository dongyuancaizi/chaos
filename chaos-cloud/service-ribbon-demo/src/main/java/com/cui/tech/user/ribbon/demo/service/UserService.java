package com.cui.tech.user.ribbon.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String home() {
        return restTemplate.getForObject("http://SERVER-DEMO/home", String.class);
    }

    public String hiError() {
        return "hi," + ",sorry,error!";
    }
}
