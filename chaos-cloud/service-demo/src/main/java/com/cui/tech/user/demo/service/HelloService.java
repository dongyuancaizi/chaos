package com.cui.tech.user.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {


    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return name;
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

}