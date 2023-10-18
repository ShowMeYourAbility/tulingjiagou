package com.llmcu.rocketmq.springboot.consumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Component;

/**
 * 此消费者用于对应那种会等待消费者响应的请求
 */
@Component
@RocketMQMessageListener(consumerGroup = "SpringbootConsumerGroup2", topic = "hello_mq_from_springboot2",consumeMode= ConsumeMode.CONCURRENTLY)
public class SpringConsumer2 implements RocketMQReplyListener<String,String> {
    @Override
    public String onMessage(String message) {
        System.out.println("Received message : "+ message);
        return message+":received";
    }
}
