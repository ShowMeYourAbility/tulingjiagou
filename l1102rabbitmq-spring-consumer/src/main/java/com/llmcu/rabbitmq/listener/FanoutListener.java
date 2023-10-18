package com.llmcu.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class FanoutListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        //打印消息
        System.out.println("fanout:"+new String(message.getBody()));
    }
}
