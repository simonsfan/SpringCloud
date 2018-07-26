package com.simons.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.simons.cn.mapper")
@EnableDiscoveryClient  //该注解标识这个一个该服务包含了Eureka Client
public class UserProviderEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderEurekaApplication.class,args);
    }
}
