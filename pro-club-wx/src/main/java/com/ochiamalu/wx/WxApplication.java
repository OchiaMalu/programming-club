package com.ochiamalu.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微信启动类
 *
 * @author OchiaMalu
 * @date 2024/08/14
 */
@SpringBootApplication
@ComponentScan("com.ochiamalu")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class, args);
    }
}
