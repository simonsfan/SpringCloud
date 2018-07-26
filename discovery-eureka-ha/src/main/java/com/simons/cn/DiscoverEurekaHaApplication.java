package com.simons.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoverEurekaHaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoverEurekaHaApplication.class,args);
    }
}
