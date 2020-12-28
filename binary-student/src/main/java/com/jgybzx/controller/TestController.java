package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jgybzx
 * @date 2020/12/25 16:07
 * @description
 */
@RestController
@RequestMapping(value = {"/n3s/propose-sip", ""})
public class TestController {
    @PostMapping("test123")
    public String queryAll() {
        return "success";
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        //这里直接添加会报错
        //list.add("a");
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        //但是通过反射添加，是可以的
        add.invoke(list, "k");
        Student student = new Student();
        student.setAddress("121212");
        add.invoke(list, student);
        String s = JsonUtil.toJson(list.get(2));
        System.out.println(s);
        System.out.println(list);

    }
}
