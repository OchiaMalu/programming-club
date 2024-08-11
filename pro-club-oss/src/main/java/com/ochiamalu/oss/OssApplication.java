package com.ochiamalu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * oss服务启动类
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
@SpringBootApplication
@ComponentScan("com.ochiamalu")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
