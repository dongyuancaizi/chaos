package com.cui.tech.boot.demo.web.controller;

import com.cui.tech.chaos.annotation.WxminiLoginToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author J.C
 * @date 2020/1/29 16:25
 */
@RequestMapping("/web")
public class IndexController {
    @WxminiLoginToken
    @GetMapping("/index")
    public void index(){
        System.out.println("index");
    }
    @PostMapping("/index1")
    public void index1(){
        System.out.println("index1");
    }
}
