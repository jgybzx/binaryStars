package com.jgybzx.controller;

import com.jgybzx.utils.Md5Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public static void main(String[] args) {
        String md5 = Md5Util.md5("P000000018495");
        System.out.println(md5);
    }


}
