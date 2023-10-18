package com.llmcu.tuling.l1102;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    //1.注入 RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testHelloWorld(){
        //2.发送消息

//        rabbitTemplate.convertAndSend("spring_queue","hello world spring....");
        rabbitTemplate.convertAndSend("spring_queue11","hello world spring....");
    }


    /**
     * 发送fanout消息
     */
    @Test
    public void testFanout(){
        //2.发送消息

//        rabbitTemplate.convertAndSend("my_spring_fanout_exchange","","spring fanout....");
        rabbitTemplate.convertAndSend("my_spring_fanout_exchange1","","spring fanout....");
    }


    @Test
    public void testDirect(){
        //2.发送消息

//        rabbitTemplate.convertAndSend("my_spring_direct_exchange","info","spring Direct....");
        rabbitTemplate.convertAndSend("my_spring_direct_exchange","info1","spring Direct....");
        rabbitTemplate.convertAndSend("my_spring_direct_exchange1","info","spring Direct....");
    }

    /**
     * 发送topic消息
     */
    @Test
    public void testTopics(){
        //2.发送消息

        rabbitTemplate.convertAndSend("my_spring_topic_exchange","baiqi.hehe","spring topic....");
        rabbitTemplate.convertAndSend("my_spring_topic_exchange","itcast.hehe","spring topic2....");
//        // 正确exchange，错误routingKey，触发return事件
//        rabbitTemplate.convertAndSend("my_spring_topic_exchange","abc.hehe","spring topic2....");
//        // 错误exchange触发confirm nack事件
//        rabbitTemplate.convertAndSend("my_spring_topic_exchange1","abc.hehe","spring topic2....");
    }

    @Test
    public void testAck(){
        rabbitTemplate.convertAndSend("my_spring_ack_exchange","ack.test","spring ack1....");
        rabbitTemplate.convertAndSend("my_spring_ack_exchange","ack.test","spring ack2....");
        rabbitTemplate.convertAndSend("my_spring_ack_exchange","ack.test","spring ack3....");
        rabbitTemplate.convertAndSend("my_spring_ack_exchange","ack.test","spring ack4....");
    }

    @Test
    public void testQos(){
        rabbitTemplate.convertAndSend("my_spring_qos_exchange","qos.test","spring qos1....");
        rabbitTemplate.convertAndSend("my_spring_qos_exchange","qos.test","spring qos2....");
        rabbitTemplate.convertAndSend("my_spring_qos_exchange","qos.test","spring qos3....");
        rabbitTemplate.convertAndSend("my_spring_qos_exchange","qos.test","spring qos4....");
    }

    /**
     * 发送测试死信消息：
     *  1. 过期时间
     *  2. 长度限制
     *  3. 消息拒收
     */
    @Test
    public void testDlx(){
        //1. 测试过期时间，死信消息
//        rabbitTemplate.convertAndSend("my_spring_dlx_exchange","test.dlx.hehe","死信消息测试1，开始...");

        //2. 测试长度限制后，消息死信
//        for (int i = 0; i < 20; i++) {
//            rabbitTemplate.convertAndSend("my_spring_dlx_exchange","test.dlx.hehe","死信消息测试+"+i+"，开始...");
//        }

        //3. 测试消息拒收
        rabbitTemplate.convertAndSend("my_spring_dlx_exchange","test.dlx.baiqi","死信消息测试3，开始...");

    }

    /*
     * 测试延时消息
     * */
    @Test
    public  void testDelay() throws InterruptedException {
        //1.发送订单消息。 将来是在订单系统中，下单成功后，发送消息
        rabbitTemplate.convertAndSend("order_exchange","order.msg","订单信息：id=1,time=2020年12月...");


        //2.打印倒计时10秒
        for (int i = 10; i > 0 ; i--) {
            System.out.println(i+"...");
            Thread.sleep(1000);
        }

    }
}
