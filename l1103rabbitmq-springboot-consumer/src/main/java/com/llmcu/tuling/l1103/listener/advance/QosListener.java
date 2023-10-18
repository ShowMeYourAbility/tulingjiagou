package com.llmcu.tuling.l1103.listener.advance;

import com.llmcu.tuling.l1103.constant.Action;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/8 23:50
 */
@Component
@Slf4j
public class QosListener {

    @RabbitListener(queues = "boot_qos_queue")
    public void ListenerQosQueue(String content, Message message, Channel channel) {
        Action action = Action.ACCEPT;
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("消费者收到消息：{}", content);
            log.info("消费者收到消息：{}", new String(message.getBody()));
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            //根据异常种类决定是ACCEPT、RETRY还是 REJECT
            action = Action.RETRY;
            log.error("消费者接收消息发生异常：", e);
        } finally {
            try {
                if (action == Action.ACCEPT) {
                    // 确认收到消息，消息将被队列移除；false只确认当前consumer一个消息收到，true确认所有consumer获得的消息。
//                    channel.basicAck(tag, true);
                } else {
                    //确认否定消息，第一个boolean表示一个consumer还是所有，第二个boolean表示requeue是否重新回到队列，true重新入队
                    channel.basicNack(tag, true, true);
                }
            } catch (IOException e) {
                log.error("消费者接收消息发生异常：", e);
            }
        }
    }

}
