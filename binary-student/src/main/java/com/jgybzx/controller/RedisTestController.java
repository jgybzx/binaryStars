package com.jgybzx.controller;

import com.alibaba.fastjson.JSON;
import com.jgybzx.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    public StringRedisTemplate stringRedisTemplate;

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
        return flag ? "缓存成功" : "缓存失败";
    }

    @PostMapping("setObjectString")
    public String setObjectString(@RequestBody Student student) {
        boolean flag = false;
        try {
            stringRedisTemplate.opsForValue().set("studentString", JSON.toJSONString(student));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag ? "缓存成功" : "缓存失败";
    }
}
