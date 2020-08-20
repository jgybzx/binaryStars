package com.jgybzx.controller;

import com.jgybzx.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author jgybzx
 * @date 2020/08/18 12:25
 * @description
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/queryAll")
    public String queryAll() {
        return studentService.queryAll();
    }

    public static void main(String[] args) {
        String s= "11111";
        String[] split = s.split("\\.");
        System.out.println(split[0]);
        String s1= "11111";
        String[] split1 = s.split("\\.");
        System.out.println(split[0]);
    }
}
