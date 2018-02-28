package com.sunjet.sunjetsecuritydemo.service.impl;

import com.sunjet.sunjetsecuritydemo.service.HelloService;
import org.springframework.stereotype.Service;

@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name;
    }
}
