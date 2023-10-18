package com.llmcu.tuling.l1103;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/8 23:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerApplicationTest {

    //注入 RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendTopic(){
        rabbitTemplate.convertAndSend("boot_topic_exchange","boot.haha","boot topic mq...");
//        rabbitTemplate.convertAndSend("boot_topic_exchange1","boot.haha","boot topic mq...");
//        rabbitTemplate.convertAndSend("boot_topic_exchange","boot1.haha","boot topic mq...");
    }

    @Test
    public void sendDirect(){
        rabbitTemplate.convertAndSend("boot_direct_exchange","info","boot direct mq...");
    }

    @Test
    public void sendFanout(){
        rabbitTemplate.convertAndSend("boot_fanout_exchange","","boot fanout mq...");
    }

    @Test
    public void sendWorkQueue(){
        rabbitTemplate.convertAndSend("","boot_workqueue_queue","boot workqueue mq...");
    }

    @Test
    public void sendAck(){
//        rabbitTemplate.convertAndSend("boot_ack_exchange","boot.ack.queue","boot ack mq1...");
        rabbitTemplate.convertAndSend("boot_ack_exchange","boot.ack.queue","boot ack mq2...");
//        rabbitTemplate.convertAndSend("boot_ack_exchange","boot.ack.queue","boot ack mq3...");
//        rabbitTemplate.convertAndSend("boot_ack_exchange","boot.ack.queue","boot ack mq4...");
    }

    @Test
    public void sendQos(){
        rabbitTemplate.convertAndSend("boot_qos_exchange","boot.qos.queue","boot qos mq1...");
        rabbitTemplate.convertAndSend("boot_qos_exchange","boot.qos.queue","boot qos mq2...");
        rabbitTemplate.convertAndSend("boot_qos_exchange","boot.qos.queue","boot qos mq3...");
        rabbitTemplate.convertAndSend("boot_qos_exchange","boot.qos.queue","boot qos mq4...");
    }
}