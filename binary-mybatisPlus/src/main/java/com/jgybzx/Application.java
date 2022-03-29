package com.jgybzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jgybzx
 * @date 2020/12/9 10:45
 * @description
 */
@SpringBootApplication
@MapperScan("com.jgybzx.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
