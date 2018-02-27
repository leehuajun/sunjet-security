package com.sunjet.sunjetsecuritydemo.web.controller;

import com.sunjet.sunjetsecuritydemo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query(@RequestParam(required = false) String username){
        System.out.println(username);
        List<User> users = Arrays.asList(
                new User("zhangsan","123456"),
                new User("lisi","123456"),
                new User("wangwu","123456")
        );
        return users;
    }
}
