package com.jgybzx.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author jgybzx
 * @date 2020/11/12 14:02
 * @description
 */
public class LoginFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("自定义过滤器filter2222222触发,拦截url:" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
