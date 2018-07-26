package com.simons.cn.util;

import com.simons.cn.UserFeignService;
import org.springframework.stereotype.Component;

/**
 * 在Feign中使用Hystrix，需要实现自定义Feign接口
 */
@Component
public class UserFeignServiceFallBack implements UserFeignService {
    @Override
    public CommonResult getUserByName(String name) {
        return CommonResult.success(CommonEnum.FAIL.getCode(), CommonEnum.FAIL.getMessage(), null);
    }
}
