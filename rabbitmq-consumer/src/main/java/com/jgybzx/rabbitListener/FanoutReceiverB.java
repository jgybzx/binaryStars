package com.jgybzx.rabbitListener;

import com.jgybzx.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * des: 扇形交换机  消费者A
 *
 * @author jgybzx
 * @date 2021/7/19 17:58
 */
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverB消费者收到消息  : " + JsonUtil.toJson(testMessage));
    }

}
