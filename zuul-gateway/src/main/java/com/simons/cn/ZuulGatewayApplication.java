package com.simons.cn;

import com.simons.cn.bean.PreRequestZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy    //表示开启Zuul代理
public class ZuulGatewayApplication {

    @Bean
    public PreRequestZuulFilter preRequestZuulFilter(){
        return new PreRequestZuulFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class,args);
    }
}
