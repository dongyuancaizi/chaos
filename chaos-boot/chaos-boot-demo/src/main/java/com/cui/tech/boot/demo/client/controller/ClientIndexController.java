package com.cui.tech.boot.demo.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author J.C
 * @date 2020/1/29 16:25
 */
@RestController
@RequestMapping("/client")
public class ClientIndexController {
    @GetMapping("/index")
    public void index(){
        System.out.println("index");
    }
    @PostMapping("/index1")
    public void index1(){
        System.out.println("index1");
    }
}
