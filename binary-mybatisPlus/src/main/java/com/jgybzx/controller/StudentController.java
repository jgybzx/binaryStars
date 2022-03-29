package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.entity.Student;
import com.jgybzx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
    private StudentService studentService;

    @PostMapping("queryAll")
    public String queryAll() {
        List<Student> studentList = studentService.queryAll();
        // int i = 1 / 0;
        studentList = studentList.stream().sorted(Comparator.comparing(Student::getBirthday)).collect(Collectors.toList());
        return JsonUtil.toJson(studentList);
    }
    @PostMapping("test")
    public String test() {
        // int i = 1 / 0;
        return "hello";
    }

}

