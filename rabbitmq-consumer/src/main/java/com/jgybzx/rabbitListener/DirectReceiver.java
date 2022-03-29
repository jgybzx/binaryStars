package com.jgybzx.rabbitListener;

import com.jgybzx.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * des: 直连交换机监听
 * queues = "TestDirectQueue" 监听队列的名字
 *
 * @author jgybzx
 * @date 2021/7/19 17:11
 */
@Component
@RabbitListener(queues = "directQueue")
public class DirectReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    
    @RabbitHandler
    public void process(Map testMessage) {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("一：DirectReceiver消费者收到消息:{}", JsonUtil.toJson(testMessage));
    }
}
