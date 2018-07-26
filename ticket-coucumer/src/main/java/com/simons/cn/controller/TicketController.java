package com.simons.cn.controller;

import com.simons.cn.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class TicketController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ticketpurchase")
    public CommonResult purchaseTicket(@RequestParam(required = false,value = "name") String name){
        //模拟判断用户身份
        CommonResult result = restTemplate.getForObject("http://localhost:8000/getuserinfo?name=" + name, CommonResult.class);
        //买票下单逻辑略
        return result;
    }

}
