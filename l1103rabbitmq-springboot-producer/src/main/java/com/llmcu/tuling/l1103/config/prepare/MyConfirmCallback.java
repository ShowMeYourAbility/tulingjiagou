package com.llmcu.tuling.l1103.config.prepare;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/9 11:39
 */
@Component
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback{
    /**
     *
     * @param correlationData 相关配置信息
     * @param ack   exchange交换机 是否成功收到了消息。true 成功，false代表失败
     * @param cause 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("confirm方法被执行了....");

        //ack 为  true表示 消息已经到达交换机
        if (ack) {
            //接收成功
            System.out.println("接收成功消息" + cause);
        } else {
            //接收失败
            System.out.println("接收失败消息" + cause);
            //做一些处理，让消息再次发送。
        }
    }
}
