package com.jgybzx.service.impl;

import com.jgybzx.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author jgybzx
 * @date 2020/9/2 11:18
 * @description
 */
@Service
public class RemoteServiceImpl implements RemoteService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String queryAll() {
        String forObject = restTemplate.getForObject("http://localhost:8081/student/queryAll", String.class);
        return forObject;
    }
}
