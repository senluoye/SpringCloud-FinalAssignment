package com.qks.commodity;

import com.qks.feignclient.config.DefaultFeignConfiguration;
import com.qks.feignclient.config.MyRulerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 15998
 */
@EnableFeignClients(basePackages = "com.qks.feignclient", defaultConfiguration = DefaultFeignConfiguration.class)
@RibbonClient(name = "commodityservice", configuration = MyRulerConfig.class)
@SpringBootApplication
public class CommodityServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommodityServerApplication.class, args);
    }

    protected SpringApplicationBuilder springApplicationBuilder(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
