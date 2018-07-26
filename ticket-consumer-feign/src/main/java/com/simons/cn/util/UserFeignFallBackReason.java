package com.simons.cn.util;

import com.simons.cn.UserFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 通过实现FallBackFactory接口的类来打印服务调用回退的原因
 */
@Slf4j
@Component
public class UserFeignFallBackReason implements FallbackFactory {
    @Override
    public UserFeignService create(final Throwable throwable) {
       return new UserFeignService() {
            @Override
            public CommonResult getUserByName(String name) {
                log.error("UserFeignService fallback reason was:"+throwable);
                return CommonResult.success(CommonEnum.FAIL.getCode(), CommonEnum.FAIL.getMessage(), null);
            }
        };
    }
}
