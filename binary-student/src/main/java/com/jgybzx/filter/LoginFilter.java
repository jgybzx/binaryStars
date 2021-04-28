package com.jgybzx.filter;

import com.jgybzx.config.FilterRequestConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/11/12 14:02
 * @description 自定义过滤器，如果使用FilterRegistrationBean 进行配置，则不需要写@Component，否则会执行两次
 */
//@Component
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //处理不需要用户信息得请求
        String requestUri = request.getRequestURI();
        // 实例化bean工厂，取出配置bean
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        FilterRequestConfig filterRequestConfig = (FilterRequestConfig) factory.getBean("filterRequestConfig");
        Map<String, String> unFilterRequestMap = filterRequestConfig.getUnFilterRequest();
        if (unFilterRequestMap != null) {
            boolean flag = unFilterRequestMap.containsValue(requestUri);
            if (flag) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        
        System.out.println("自定义过滤器filter1触发,拦截url:" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
