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
        String s = "http://10.30.87.54/ns3-propose-make";
        String s1 = "http://10.30.77.110.8090";
        String[] split = s.split("/");
        String[] split1 = s1.split("/");
        String[] split2 = split[split.length - 1].split("\\.");
        String[] split3 = split1[split1.length - 1].split("\\.");
        System.out.println("1");

        System.out.println("1");
        System.out.println("1");
        System.out.println("1");


    }
}
