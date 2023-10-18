package com.llmcu.tuling.l1103.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/8 23:50
 */
@Component
public class RabbitMQListener {

    //定义方法进行信息的监听   RabbitListener中的参数用于表示监听的是哪一个队列
    @RabbitListener(queues = "boot_topic_queue")
    public void ListenerTopicQueue(Message message){
        System.out.println("message:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_direct_queue")
    public void ListenerDirectQueue(Message message){
        System.out.println("message:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_fanout_queue")
    public void ListenerFanoutQueue(Message message){
        System.out.println("message:"+new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_workqueue_queue")
    public void ListenerWorkqueueQueue(Message message){
        System.out.println("message:"+new String(message.getBody()));
    }
}
