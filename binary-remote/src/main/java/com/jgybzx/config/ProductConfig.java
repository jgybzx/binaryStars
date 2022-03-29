package com.jgybzx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jgybzx
 * @date 2020/9/8 9:30
 * @description
 */
@Component
@ConfigurationProperties("product")
public class ProductConfig {
    public List<String> getProductNo() {
        return productNo;
    }

    public void setProductNo(List<String> productNo) {
        this.productNo = productNo;
    }

    private List<String> productNo;

}
