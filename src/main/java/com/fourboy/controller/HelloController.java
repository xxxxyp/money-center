package com.fourboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/subject")
    @ResponseBody
    public Map getInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("subjuct","科目");
        return map;
    }
}
