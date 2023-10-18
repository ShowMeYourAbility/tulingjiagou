package com.llmcu.tuling.l1103.config.prepare;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/9 11:41
 */
@Component
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {
    /**
     * @param message    消息对象
     * @param replyCode  错误码
     * @param replyText  错误信息
     * @param exchange   交换机
     * @param routingKey 路由键
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return 执行了....");

        System.out.println("message:" + message);
        System.out.println("replyCode:" + replyCode);
        System.out.println("replyText:" + replyText);
        System.out.println("exchange:" + exchange);
        System.out.println("routingKey:" + routingKey);

        //处理
    }
}
