package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello(){
        return port;
    }

    @RequestMapping("/errors")
    public String errors(){
        // 手动模拟异常
        System.out.println(1/0);
        return "error";
    }
}
