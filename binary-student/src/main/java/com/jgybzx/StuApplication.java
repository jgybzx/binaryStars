package com.jgybzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * @author jgybzx
 * @date 2020/9/1 15:36
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.jgybzx.mappers")
@EnableTransactionManagement
public class StuApplication {
    @Bean
    public RestTemplate restTemplate() {
        //restTemplate 远程调用 必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);
        return new RestTemplate(httpRequestFactory);
    }
    /**
     * @EnableEurekaClient
     */
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class, args);
    }
}
