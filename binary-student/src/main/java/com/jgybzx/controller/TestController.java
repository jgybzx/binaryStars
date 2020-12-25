package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
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
}
