package com.qks.indent.service;

import com.qks.common.entity.Indent;
import com.qks.indent.mapper.IndentMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalTime;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-15 16:18
 */
@Service
@RabbitListener(queues = "order.release.queue")
@Transactional(rollbackFor = Exception.class)
public class OrderReleaseQueueService {

    @Resource
    private IndentMapper indentMapper;

    @RabbitHandler
    public void getReleaseOrderMessage(Message message, String data, Channel channel) throws IOException {
        System.out.println("存在订单过时：" + LocalTime.now());
        System.out.println("订单id：" + data);
        // 确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        Indent indent = indentMapper.getIndentByIndentId(data);
        // 没支付，取消订单
        if (indent.getStatus() == 0) {
            indentMapper.updateIndentByIndentId(data, -1);
        }
    }
}

