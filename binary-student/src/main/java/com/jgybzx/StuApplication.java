package com.jgybzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jgybzx
 * @date 2020/9/1 15:36
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jgybzx.mappers")
@EnableTransactionManagement
@EnableScheduling
public class StuApplication {
    /**
     * @EnableEurekaClient
     */
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class, args);
    }
}
