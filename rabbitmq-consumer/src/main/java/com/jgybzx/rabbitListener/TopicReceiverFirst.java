package com.jgybzx.rabbitListener;

import com.jgybzx.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * des: 主题交换机
 * queues = "topic.first" 监听队列的名字
 *
 * @author jgybzx
 * @date 2021/7/19 17:11
 */
@Component
@RabbitListener(queues = "topic.first")
public class TopicReceiverFirst {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(Map testMessage) {
        logger.info("TopicReceiverFirst消费者收到消息:{}", JsonUtil.toJson(testMessage));
    }
}
