package com.simons.cn;

import com.simons.cn.util.CommonResult;
import com.simons.cn.util.UserFeignFallBackReason;
import com.simons.cn.util.UserFeignServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-provider",fallback = UserFeignServiceFallBack.class,fallbackFactory = UserFeignFallBackReason.class)
public interface UserFeignService {

    @RequestMapping(value = "/getuserinfo",method = RequestMethod.GET)
    CommonResult getUserByName(@RequestParam(required = false,value = "name")  String name);

}
