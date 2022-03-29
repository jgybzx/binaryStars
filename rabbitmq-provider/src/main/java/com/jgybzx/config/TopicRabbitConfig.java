package com.jgybzx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * des: Topic Exchange 主题交换机
 *
 * @author jgybzx
 * @date 2021/7/19 17:27
 */
@Configuration
public class TopicRabbitConfig {

    public final static String FIRST = "topic.first";
    public final static String SECOND = "topic.second";


    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitConfig.FIRST);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitConfig.SECOND);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将 第一个队列绑定到交换机上，绑定的键值为 topic.first
     * 此时只有消息携带的 路由键是 topic.first 才会发给这个队列
     *
     * @return org.springframework.amqp.core.Binding
     * @author jgybzx
     * @date 2021/7/19 17:35
     */
    @Bean
    Binding bindingTopicExchangeOne() {
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(FIRST);
    }

    /**
     * 将 第一个队列绑定到交换机上，绑定的键值为 topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     *
     * @return org.springframework.amqp.core.Binding
     * @author jgybzx
     * @date 2021/7/19 17:35
     */
    @Bean
    Binding bindingTopicExchangeTwo() {
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }

}
