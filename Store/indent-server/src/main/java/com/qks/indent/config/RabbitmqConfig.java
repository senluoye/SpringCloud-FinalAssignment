package com.qks.indent.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-15 16:03
 */
@Configuration
public class RabbitmqConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 创建订单事件交换机
    @Bean
    public Exchange orderEventExchange(){
        return ExchangeBuilder.directExchange("order.event.exchange").durable(true).build();
    }

    // 创建延时队列
    @Bean
    public Queue orderDelayQueue(){
        // 创建队列需要的参数
        Map<String, Object> args = new HashMap<>(3);
        // 设置TTL
        args.put("x-message-ttl", 10 * 60 * 1000);
        // 设置死信的目的交换机
        args.put("x-dead-letter-exchange", "order.event.exchange");
        // 设置死信交给目的交换机时的路由键
        args.put("x-dead-letter-routing-key", "order.release");
        return QueueBuilder.durable("order.delay.queue").withArguments(args).build();
    }

    // 创建死信队列
    @Bean
    public Queue orderReleaseQueue(){
        return QueueBuilder.durable("order.release.queue").build();
    }

    // 设置交换机和延时队列的绑定关系
    @Bean
    public Binding bindingDelayQueue(@Qualifier("orderEventExchange") Exchange exchange, @Qualifier("orderDelayQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("order.create").noargs();
    }

    // 设置交换机和死信队列的绑定关系
    @Bean
    public Binding bindingReleaseQueue(@Qualifier("orderEventExchange") Exchange exchange, @Qualifier("orderReleaseQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("order.release").noargs();
    }
}
