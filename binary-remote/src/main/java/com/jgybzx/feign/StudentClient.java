package com.jgybzx.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


/**
 * @author jgybzx
 * @date 2020/9/3 14:48
 * @description
 */
@FeignClient(value = "binary-student", fallback = StudentClientFallback.class)
public interface StudentClient {
    /**
     * 远程调用，根据入参条件查询数据
     *
     * @param map
     * @return
     */
    @PostMapping("student/queryByCondition")
    String queryByCondition(@RequestBody Map<String, Object> map);
}
