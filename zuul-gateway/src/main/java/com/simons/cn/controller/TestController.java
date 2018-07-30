package com.simons.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        return "hello world !";
    }

    @ResponseBody
    @GetMapping("/getuserinfo")
    public String getUserInfo(){

        return "hello world !";
    }



}
