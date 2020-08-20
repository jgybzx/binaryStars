package com.jgybzx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jgybzx
 * @date 2020/08/18 13:41
 * @description
 */
@RestController
public class Test {
    @RequestMapping("1")
    public String show(){
        return "1";
    }
}
