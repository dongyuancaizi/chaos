package com.cui.tech.boot.demo.manager.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author J.C
 * @date 2020/1/29 16:25
 */
@RequestMapping("/manage")
@RestController
public class IndexController {
    @GetMapping("/index")
    public void index(){
        System.out.println("index");
    }
    @PostMapping("/index1")
    public void index1(){
        System.out.println("index1");
    }
}
