package com.fourboy.controller;

import com.fourboy.bean.UserInfo;
import com.fourboy.bean.res.BaseObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆接口
 */
@RestController
@Slf4j
public class LoginController {


    @PostMapping("/user/login")
    public BaseObj<UserInfo> login(){
        log.info("");
        BaseObj<UserInfo> result = new BaseObj<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("001");
        userInfo.setUserName("Don");
        userInfo.setPwd("123");
        result.setData(userInfo);
        return result;
    }
}
