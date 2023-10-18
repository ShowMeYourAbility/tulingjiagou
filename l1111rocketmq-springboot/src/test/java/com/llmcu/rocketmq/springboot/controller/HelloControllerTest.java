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
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/1 12:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloControllerTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    String springTopic = "hello_mq_from_springboot";
    SendResult sendResult;

    /**
     * 参数destination拼接”：tagx“,即设置tag为tagx
     */
    @Test
    public void sendMsg() {
        sendResult = rocketMQTemplate.syncSend(springTopic, "Hello, World!");
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        sendResult = rocketMQTemplate.syncSend(springTopic, new User().setUserAge((byte) 18).setUserName("Kitty"));
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
//        sendResult = rocketMQTemplate.syncSend(springTopic+":tagx", "Hello, World!");
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
//        sendResult = rocketMQTemplate.syncSend(springTopic+":tagy", new User().setUserAge((byte) 18).setUserName("Kitty"));
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
    }

    /**
     * 其实sendMsg()底层用的也是MessageBuilder
     */
    @Test
    public void sendMsg2() {
        // 1、MessageBuilder
        /*
        sendResult = rocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload(
                new User().setUserAge((byte) 21).setUserName("Lester")).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        */

        // 2、参数destination拼接”：tagx“,即设置tag为tagx
        /*
        sendResult = rocketMQTemplate.syncSend(springTopic+":tagc", MessageBuilder.withPayload(
                new User().setUserAge((byte) 21).setUserName("Lester")).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        */

        // 3、在header中设置tag没有作用
        sendResult = rocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload(
                        new User().setUserAge((byte) 21).setUserName("Lester"))
                .setHeader(RocketMQHeaders.TAGS, "tag_d")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
    }

    // 异步发送
    @Test
    public void sendMsg3() {
        rocketMQTemplate.asyncSend(springTopic, new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }
        });

    }

    /**
     * 不用builder的另一种方法转换为Message，用于发送
     */
    @Test
    public void sendMsg4() {
        //发送指定TAG的消息
        rocketMQTemplate.convertAndSend(springTopic + ":tag0", "I'm from tag0");  // tag0 will not be consumer-selected
        System.out.printf("syncSend topic %s tag %s %n", springTopic, "tag0");
        rocketMQTemplate.convertAndSend(springTopic + ":tag1", "I'm from tag1");
        System.out.printf("syncSend topic %s tag %s %n", springTopic, "tag1");
    }






}