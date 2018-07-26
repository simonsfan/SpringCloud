package com.simons.cn.controller;

import com.simons.cn.bean.User;
import com.simons.cn.service.UserServiceImpl;
import com.simons.cn.util.CommonEnum;
import com.simons.cn.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/getuserinfo")
    public CommonResult getUserInfo(@RequestParam(required = false,value = "name") String name){
        List<User> userList = userService.getUserByName(name);
        return CommonResult.success(CommonEnum.SUCESS.getCode(),CommonEnum.SUCESS.getMessage(),userList);
    }
}
