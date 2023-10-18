package com.llmcu.rabbitmq.listener.advance;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/9 20:03
 */
@Component
@Slf4j
public class DlxDlListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicNack(deliveryTag, true, false);
    }
}
