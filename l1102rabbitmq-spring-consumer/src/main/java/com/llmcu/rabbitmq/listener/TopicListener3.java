package com.llmcu.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class TopicListener3 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //打印消息
        System.out.println("topic3:"+new String(message.getBody()));
    }
}
