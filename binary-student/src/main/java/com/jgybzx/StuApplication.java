package com.jgybzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jgybzx
 * @date 2020/08/18 12:25
 * @description
 */
@SpringBootApplication
//@EnableEurekaClient
@ComponentScan("com.jgybzx.mapper")
public class StuApplication {
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class, args);
    }
}
