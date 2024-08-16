package com.ochiamalu.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 *
 * @author OchiaMalu
 * @date 2024/08/06
 */
@SpringBootApplication
@ComponentScan("com.ochiamalu")
@MapperScan("com.ochiamalu.**.mapper")
@EnableFeignClients(basePackages = "com.ochiamalu")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class, args);
    }
}
