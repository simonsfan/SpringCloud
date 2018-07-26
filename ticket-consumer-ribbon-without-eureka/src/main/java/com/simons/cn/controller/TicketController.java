package com.simons.cn.controller;

import com.simons.cn.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class TicketController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/ticketpurchase")
    public CommonResult purchaseTicket(@RequestParam(required = false, value = "name") String name) {
        CommonResult result = restTemplate.getForObject("http://user-provider/" + "getuserinfo?name=" + name, CommonResult.class);
        return result;
    }
      @GetMapping("/loginfo")
    public void loginfo() {
          ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
          log.info("host=" + serviceInstance.getHost() + ",port=" + serviceInstance.getPort() + ",serviceid=" + serviceInstance.getServiceId());
    }
}
