package com.fourboy.controller;

import com.fourboy.bean.UserInfo;
import com.fourboy.bean.res.BaseObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆接口
 */
@RestController
@Slf4j
public class LoginController {


    @GetMapping("/login")
    public BaseObj<UserInfo> login(){
        log.info("");
        BaseObj<UserInfo> result = new BaseObj<>();
        result.setMessage("尚未登陆，请登录！");
        return result;
    }
}
