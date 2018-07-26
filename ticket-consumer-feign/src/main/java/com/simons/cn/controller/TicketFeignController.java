package com.simons.cn.controller;

import com.simons.cn.UserFeignService;
import com.simons.cn.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TicketFeignController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/ticketpurchase")
    public CommonResult purchaseTicket(@RequestParam(required = false,value = "name") String name){
        CommonResult result = userFeignService.getUserByName(name);
        return result;
    }

}
