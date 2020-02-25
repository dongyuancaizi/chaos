package com.cui.tech.user.feign.demo.service;

import com.cui.tech.user.feign.demo.ifz.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne() {
        return "sorry ooo";
    }
}