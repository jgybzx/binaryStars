package com.jgybzx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * des: 直连型交换机  交换机 根据携带的路由键 routing key，匹配相应的队列
 *
 * @author jgybzx
 * @date 2021/7/19 16:14
 */
@Configuration
public class DirectRabbitConfig {
    /**
     * 队列
     *
     * @return org.springframework.amqp.core.Queue
     * @author jgybzx
     * @date 2021/7/19 16:37
     */
    @Bean
    public Queue directQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("directQueue", true);
    }

    /**
     * 交换机
     *
     * @return org.springframework.amqp.core.DirectExchange
     * @author jgybzx
     * @date 2021/7/19 16:39
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange", true, false);
    }

    /**
     * 绑定
     *
     * @return org.springframework.amqp.core.Binding
     * @author jgybzx
     * @date 2021/7/19 16:42
     */
    @Bean
    Binding bindingDirect() {
        // bind().to().with(); 将队列绑定到交换机上，routing key = TestDirectRouting
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("TestDirectRouting");
    }

}
