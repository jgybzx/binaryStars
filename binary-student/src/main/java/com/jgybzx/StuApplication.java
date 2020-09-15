package com.jgybzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author jgybzx
 * @date 2020/9/1 15:36
 * @description
 */
@SpringBootApplication

@MapperScan("com.jgybzx.mappers")
public class StuApplication {
    /**
     * @EnableEurekaClient
     */
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class, args);
    }
}
