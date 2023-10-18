package com.llmcu.rabbitmq.listener.advance;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/9 20:03
 */
@Component
@Slf4j
public class QosListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //1、获取消息的id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {


            //2、获取消息
            String messageBody = new String(message.getBody());
            log.info("消费者接收到消息：{}", messageBody);

            //3、进行业务处理
            System.out.println("=====进行业务处理====");
            TimeUnit.SECONDS.sleep(1);

            //4、进行消息签收
            channel.basicAck(deliveryTag, true);

        } catch (Exception e) {

            //拒绝签收
             /*
            第三个参数：requeue：重回队列。如果设置为true，则消息重新回到queue，broker会重新发送该消息给消费端
             */
            channel.basicNack(deliveryTag, true, true);

        }
    }
}
