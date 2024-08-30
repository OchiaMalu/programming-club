package com.ochiamalu.practice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
@SpringBootApplication
@ComponentScan("com.ochiamalu")
@MapperScan("com.ochiamalu.**.dao")
public class PracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
