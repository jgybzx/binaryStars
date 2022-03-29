package com.jgybzx.service.impl;

import com.jgybzx.feign.StudentClient;
import com.jgybzx.service.RemoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author jgybzx
 * @date 2020/9/2 11:18
 * @description
 */
@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private StudentClient studentClient;

    @Override
    public String queryAll() {
        Object parm = "";
        String result1 = null;
        String result2 = null;
        String result3 = null;
        try {
            result1 = restTemplate.postForObject("http://localhost:8081/student/queryAll", parm, String.class);
            // 通过discoveryClient在eureka获取微服务的ip进行调用
            List<ServiceInstance> instances = discoveryClient.getInstances("binary-student");
            URI uri = instances.get(0).getUri();
            String url = uri + "student/queryAll";
            result2 = restTemplate.postForObject(url, parm, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        } finally {
            //通过Ribbon的负载均衡，自动获取微服务集群中的 一个服务进行调用
            //当加上@LoadBalanced时,url地址只能使用服务名字，要不然会抛出异常
            String url1 = "http://binary-student/student/queryAll";
            result3 = restTemplate.postForObject(url1, parm, String.class);
        }
        return result2 + result1 + result3;
    }

    @Override
    public String queryByCondition(Map<String, Object> map) {
        return studentClient.queryByCondition(map);
    }
}
