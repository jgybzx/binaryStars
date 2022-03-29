package com.jgybzx.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jgybzx
 * @date 2021/3/5 17:19
 * @description
 */
@Configuration
public class WebFilterConfig {
    /**
     * url-pattern匹配规则
     * 1、完全匹配：以“/”开头，以字母（非“*”）结束
     * 如：<url-pattern>/test/list.do</url-pattern>
     * 2、目录匹配：以“/”开头且以“/*”结尾
     * 如：<url-pattern>/test/*</url-pattern>
     * <url-pattern>/*</url-pattern>
     * 3、扩展名匹配：以“*.”开头，以扩展名结束
     * 如：<url-pattern>*.do</url-pattern>
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<LoginFilter> filter00TimeConsuming() {
        FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<LoginFilter>();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("*.do");
        registration.setName("LoginFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean<LoginFilter2> filter01TimeConsuming() {
//        FilterRegistrationBean<LoginFilter2> registration = new FilterRegistrationBean<LoginFilter2>();
//        registration.setFilter(new LoginFilter2());
//        registration.addUrlPatterns("/student/*");
//        registration.setName("LoginFilter2");
//        registration.setOrder(2);
//        return registration;
//    }
}
