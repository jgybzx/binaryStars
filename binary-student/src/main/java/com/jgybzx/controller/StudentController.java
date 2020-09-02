package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.mappers.StudentMapper;
import com.jgybzx.model.Student;
import com.jgybzx.service.StudentService;
import com.jgybzx.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/9/1 15:31
 * @description
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("queryAll")
    public String queryAll() {
        List<Student> studentList = studentService.queryAll();
        return JsonUtil.toJson(studentList);
    }

    @PostMapping("test")
    public String Test() {
        return "success";
    }
}

