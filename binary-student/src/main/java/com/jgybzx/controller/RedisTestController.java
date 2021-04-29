package com.jgybzx.controller;

import com.jgybzx.model.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * des: redis 使用测试
 *
 * @author jgybzx
 * @date 2021/4/29 15:28
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    @PostMapping("get")
    public Map<String, Object> redisGet(@Param("key") String key) {
        Student o = (Student) redisTemplate.opsForValue().get(key);
        Map<String, Object> map = new HashMap<>(16);
        map.put(key, o);
        return map;
    }

    @PostMapping("setObject")
    public String setObject(@RequestBody Student student) {
        boolean flag = false;
        try {
            redisTemplate.opsForValue().set("student", student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            return "缓存成功";
        } else {
            return "缓存失败";
        }
    }

}
