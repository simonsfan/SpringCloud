package com.simons.cn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.simons.cn.util.CommonEnum;
import com.simons.cn.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class TicketController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "purchaseTicketFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),   //滑动窗口大小
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),   //过多久再次检测是否开启熔断器
            @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage",value = "50")  //错误率
    })
    @RequestMapping("/ticketpurchase")
    @ResponseBody
    public CommonResult purchaseTicket(@RequestParam(required = false, value = "name") String name) {
        CommonResult result = restTemplate.getForObject("http://user-provider/getuserinfo?name=" + name, CommonResult.class);
        return result;
    }

    /**
     * 默认回退方法
     *
     * @return
     */
    public CommonResult purchaseTicketFallBack(String name) {
        return CommonResult.success(CommonEnum.FAIL.getCode(), CommonEnum.FAIL.getMessage(), null);
    }

    @GetMapping("/loginfo")
    public void loginfo() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
        log.info("host=" + serviceInstance.getHost() + ",port=" + serviceInstance.getPort() + ",serviceid=" + serviceInstance.getServiceId());
    }
}
