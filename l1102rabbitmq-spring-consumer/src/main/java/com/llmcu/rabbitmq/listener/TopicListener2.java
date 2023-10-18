package com.llmcu.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class TopicListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //打印消息
        System.out.println("topic2:"+new String(message.getBody()));
    }
}
