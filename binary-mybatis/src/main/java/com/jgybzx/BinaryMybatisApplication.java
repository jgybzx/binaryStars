package com.jgybzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jgybzx
 */
@SpringBootApplication
@MapperScan("com.jgybzx.mapper")
public class BinaryMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinaryMybatisApplication.class, args);
    }

}
