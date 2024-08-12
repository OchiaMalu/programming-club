package com.ochiamalu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关启动类
 *
 * @author OchiaMalu
 * @date 2024/08/12
 */
@SpringBootApplication
@ComponentScan("com.ochiamalu")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}
