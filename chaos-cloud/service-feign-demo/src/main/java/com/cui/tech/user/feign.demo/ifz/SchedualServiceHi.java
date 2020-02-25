package com.cui.tech.user.feign.demo.ifz;


import com.cui.tech.user.feign.demo.service.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "server-demo", fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    String sayHiFromClientOne();
}

