package com.simons.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描*Mapper.java接口文件
@MapperScan("com.simons.cn.mapper")
@SpringBootApplication
public class ProviderUserSleuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserSleuthApplication.class);
    }
}

