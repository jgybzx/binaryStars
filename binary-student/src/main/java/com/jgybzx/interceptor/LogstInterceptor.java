package com.jgybzx.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jgybzx
 * @date 2020/11/12 14:54
 * @description
 */
public class LogstInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<Object> THREADLOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getHeader("token");
        System.out.println("拦截器执行");
        LogstInterceptor.THREADLOCAL.set("a");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("请求结束");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREADLOCAL.remove();
        System.out.println("视图返回");
    }
}
