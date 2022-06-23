package com.qks.cart;

import com.qks.feignclient.config.DefaultFeignConfiguration;
import com.qks.feignclient.config.MyRulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:09
 */

@EnableFeignClients(basePackages = "com.qks.feignclient.service", defaultConfiguration = DefaultFeignConfiguration.class)
@RibbonClient(name = "cartservice", configuration = MyRulerConfig.class)
@SpringBootApplication
public class CartServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServerApplication.class, args);
    }
}
