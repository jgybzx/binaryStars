package com.jgybzx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jgybzx
 * @date 2021/3/8 9:25
 * @description
 */
@Component("filterRequestConfig")
@ConfigurationProperties(prefix = "filter")
public class FilterRequestConfig {
    private Map<String,String> unFilterRequest;

    public Map<String, String> getUnFilterRequest() {
        return unFilterRequest;
    }

    public void setUnFilterRequest(Map<String, String> unFilterRequest) {
        this.unFilterRequest = unFilterRequest;
    }
}
