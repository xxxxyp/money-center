package com.fourboy.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {

    @RequestMapping("/interceptor")
    public String verifyInterceptor(){
        return "校验拦截器";
    }
}
