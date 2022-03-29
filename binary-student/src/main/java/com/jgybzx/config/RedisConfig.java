package com.jgybzx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * des: redis配置
 * 储格式为<String, Object> 坑太多，建议还是使用 储格式为<String,String>
 * 不仅存在存储在redis里边，不方便直观的查看，而且转换的时候也会有各种坑
 *
 * @author jgybzx
 * @date 2021/4/29 15:13
 */
@Configuration
public class RedisConfig {
    /**
     * 自定义 配置序列化器，存储格式为<String, Object>
     *
     * @param factory
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.Object>
     * @author jgybzx
     * @date 2021/4/29 15:25
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value 采取 JdkSerializationRedisSerializer对POJO进行序列化
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }
}
