package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Student;
import com.jgybzx.model.StudentDto;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jgybzx
 * @date 2020/9/1 15:31
 * @description
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private StudentService studentService;

    @PostMapping("testRedis")
    public String testRedis() {
        Object getMsfUserInfoUrl = redisTemplate.opsForHash().get("dictTools_hash_cache:public", "getMsfUserInfoUrl");
        return JsonUtil.toJson("1");
    }

    @PostMapping("queryAll")
    public String queryAll() {
        List<Student> studentList = studentService.queryAll();
        // int i = 1 / 0;
        studentList = studentList.stream().sorted(Comparator.comparing(Student::getBirthday)).collect(Collectors.toList());
        return JsonUtil.toJson(studentList);
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @PostMapping("queryByCondition")
    public Map<String, Object> queryByCondition(@RequestBody Map<String, Object> map) {
        StudentDto studentDto = JsonUtil.mapToClass(map, StudentDto.class);
       /* try {
            // 模拟服务器异常，访问超时
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<Student> studentList = studentService.queryByCondition(studentDto);
        Map<String, Object> result = new HashMap<>(16);
        result.put("studentList", studentList);
        int i = 1 / 0;
        return result;
        /**
         * {
         *   "id": "901",
         *   "name": "张老大",
         *   "sex": "男",
         *   "department": "计算机系",
         *   "address": "北京市海淀区",
         *   "birthday": "1995-05-13 00:00:00"
         * }
         */
    }

    @PostMapping("import")
    public String importData(@RequestParam("file") MultipartFile file) {
        String rows = studentService.importFile(file);
        return "上传成功，共"+rows+"条数据。";
    }

    @PostMapping("testTransaction")
    public String testTransaction() {
        studentService.testTransaction();
        return "完成";
    }

    public static void main(String[] args) {
        String s = "1.2.3";
        String[] split = s.split("\\.");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i] + "asasd");
        }
    }
}

