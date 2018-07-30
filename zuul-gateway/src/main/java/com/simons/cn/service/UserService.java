package com.simons.cn.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.simons.cn.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Observable;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultfallback")
    public Observable<String> getUserInfoById(String name){

        Observable observable = Observable.just;

/*        Observable observable = new Observable();
        User userProvider = this.restTemplate.getForObject("http://user-provider/getuserinfo?name=" + name, User.class, name);
        User userComsumer = this.restTemplate.getForObject("http://ticket-consumer/", User.class, name);*/

        /*Map<String,Object> map  = new HashMap<>();
        map.put("userProvider",userProvider);
        map.put("userComsumer",userComsumer);
        return CommonResult.success(CommonEnum.SUCESS.getCode(),CommonEnum.SUCESS.getMessage(),map);*/
    }


}
