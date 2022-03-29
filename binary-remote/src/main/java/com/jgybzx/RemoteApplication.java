package com.jgybzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jgybzx
 * @date 2020/9/2 11:06
 * @description
 */
@SpringBootApplication
@EnableFeignClients
public class RemoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(RemoteApplication.class, args);
    }
}
