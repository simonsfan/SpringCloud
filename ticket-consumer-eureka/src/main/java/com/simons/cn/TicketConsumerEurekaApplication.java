package com.simons.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //该注解标识这个一个该服务包含了Eureka Client
public class TicketConsumerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketConsumerEurekaApplication.class,args);
    }

}
