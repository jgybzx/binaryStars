package com.jgybzx.controller;

import com.jgybzx.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/9/2 11:02
 * @description
 */
@RestController
@RequestMapping("remote")
public class RemoteController {

    @Autowired
    private RemoteService remoteService;

    @PostMapping("queryAll")
    public String queryAll() {
        String result = remoteService.queryAll();
        return result;
    }

    @PostMapping("queryByCondition")
    public String queryByCondition(@RequestBody Map<String, Object> map) {
        return remoteService.queryByCondition(map);
    }

    @PostMapping("test")
    public String Test() {
        return "success";
    }

    public static void main(String[] args) {
        long milliSecond = 1598265096L;
        Date date = new Date();
        date.setTime(milliSecond);
        System.err.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
    }

}
