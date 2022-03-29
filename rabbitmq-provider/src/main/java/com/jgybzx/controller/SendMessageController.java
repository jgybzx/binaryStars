package com.jgybzx.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * des:
 *
 * @author jgybzx
 * @date 2021/7/19 16:47
 */
@RestController
@RequestMapping("rabbit")
public class SendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("sendDirectMessage")
    public void sendDirectMessage() {
        // 简单发送数据
        String messageData = "sendDirectMessage";
        String routingKey = "TestDirectRouting";
        Map<String, Object> map = new HashMap<>(16);
        map.put("messageData", messageData);
        map.put("routingKey", routingKey);
        String username = rabbitTemplate.getConnectionFactory().getUsername();
        // 将消息发送到交换机 TestDirectExchange，并携带routing key = TestDirectRouting
        rabbitTemplate.convertAndSend("directExchange", routingKey, map);
    }

    @PostMapping("sendTopicMessageFirst")
    public void sendTopicMessage() {
        // 简单发送数据
        String messageData = "sendTopicMessage";
        String routingKey = "topic.first";
        Map<String, Object> map = new HashMap<>(16);
        map.put("messageData", messageData);
        map.put("routingKey", routingKey);
        rabbitTemplate.convertAndSend("topicExchange", routingKey, map);
    }

    @PostMapping("sendTopicMessageSecond")
    public void sendTopicMessage2() {
        // 简单发送数据
        String messageData = "sendTopicMessage";
        String routingKey = "topic.second";
        Map<String, Object> map = new HashMap<>(16);
        map.put("messageData", messageData);
        map.put("routingKey", routingKey);
        rabbitTemplate.convertAndSend("topicExchange", routingKey, map);
    }

    @PostMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }
}
