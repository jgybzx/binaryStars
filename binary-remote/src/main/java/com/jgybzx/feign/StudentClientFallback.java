package com.jgybzx.feign;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/9/3 16:35
 * @description feign的降级方法
 */
@Component
public class StudentClientFallback implements StudentClient {
    @Override
    public String queryByCondition(Map<String, Object> map) {
        return "服务器访问超时*************";
    }
}
