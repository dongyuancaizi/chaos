package com.cui.tech.user.ribbon.demo.controller;

import com.cui.tech.user.ribbon.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControler {
    @Autowired
    UserService userService;

    @GetMapping(value = "/home")
    public String home() {
        return userService.home();
    }
}
