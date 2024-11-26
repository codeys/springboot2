package com.springboot2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Druid
 * Redis
 * Redisson
 * Spring Cache
 * Mybatis Plus
 * quartz
 * 切面打印日志
 */

@SpringBootApplication
@MapperScan({"com.springboot2.mapper"})
public class Springboot2ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2ProjectApplication.class, args);
    }

}
