package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.config.ProductConfig;
import com.jgybzx.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    @Autowired
    private ProductConfig productConfig;

    @PostMapping("queryAll")
    public String queryAll() {
        return remoteService.queryAll();
    }

    @PostMapping("queryByCondition")
    public String queryByCondition(@RequestBody Map<String, Object> map) {
        return remoteService.queryByCondition(map);
    }

    @PostMapping("config")
    public String productList() {
        List<String> productNo = productConfig.getProductNo();
        return JsonUtil.toJson(productNo);
    }

}
