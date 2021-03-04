package com.jgybzx.controller;

import com.jgybzx.JsonUtil;
import com.jgybzx.model.Prp_Show_Component;
import com.jgybzx.model.Prp_Template_Conf;
import com.jgybzx.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author jgybzx
 * @date 2020/12/9 9:03
 * @description
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("newtouch")
public class NewtouchController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 远程调用 测试
     *
     * @param productNo
     * @return
     */
    @PostMapping("remotetest")
    public String queryAll(String productNo) {
        String url = "http://47.111.158.181:8022/n3s-propose-make/template/queryTemplate.json";
        HashMap<String, String> map = new HashMap<>();
        map.put("product_no", productNo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "0");
        headers.add("source", "0");
        HttpEntity<HashMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        String body = exchange.getBody();
        Map<String, Object> map1 = JsonUtil.jsonToMap(body);
        Object defmap = map1.get("defmap");
        Map<String, Object> map2 = JsonUtil.jsonToMap(JsonUtil.toJson(defmap));
        Object templateConf = map2.get("showComponent");

        List<Map<String, Object>> list = JsonUtil.jsonToListMap(JsonUtil.toJson(templateConf));
        List<Prp_Show_Component> list1 = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : list) {
            Prp_Show_Component prp_show_component = JsonUtil.mapToClass(stringObjectMap, Prp_Show_Component.class);
            list1.add(prp_show_component);
        }
        System.out.println(body);
        return body;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("i=" + i);
            for (int i1 = 0; i1 < 5; i1++) {
                System.out.println("i12222222222222222=" + i1);
                if (i1 == 2) {
                    break;
                }
            }
        }
    }
}
