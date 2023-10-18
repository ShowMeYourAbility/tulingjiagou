package com.llmcu.rocketmq.springboot.controller;

import com.llmcu.rocketmq.springboot.domain.OrderPaidEvent;
import com.llmcu.rocketmq.springboot.domain.User;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/1 12:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloControllerTest2 {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    String springTopic = "hello_mq_from_springboot2";
    SendResult sendResult;



    /**
     * 消费者应实现RocketMQReplyListener
     * sendAndReceive要求 消费者有回执。注意：是消费者，不是broker
     */
    @Test
    public void sendMsg5() {
        // sleep是因为在springboottest下，容器中的RocketMQReplyListener实现类可能没有建立好
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //同步发送消息并且返回一个String类型的结果。
        String replyString = rocketMQTemplate.sendAndReceive(springTopic, "request string", String.class);
        System.out.printf("send %s and receive %s %n", "request string", replyString);

        //同步发送消息并且返回一个Byte数组类型的结果。
        byte[] replyBytes = rocketMQTemplate.sendAndReceive(springTopic, MessageBuilder.withPayload("request byte[]").build(), byte[].class, 3000);
        System.out.printf("send %s and receive %s %n", "request byte[]", new String(replyBytes));
    }




}