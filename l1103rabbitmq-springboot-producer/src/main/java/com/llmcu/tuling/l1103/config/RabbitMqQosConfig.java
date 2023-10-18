package com.llmcu.tuling.l1103.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/8 23:26
 */
@Configuration
public class RabbitMqQosConfig {

    //定义交换机的名字
    public static final String  EXCHANGE_NAME = "boot_qos_exchange";
    //定义队列的名字
    public static final String QUEUE_NAME = "boot_qos_queue";

    //1、声明交换机
    @Bean("bootQosExchange")
    public Exchange bootExchange(){

        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }



    //2、声明队列
    @Bean("bootQosQueue")
    public Queue bootQueue(){

        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    //3、队列与交换机进行绑定
    @Bean("bootQosBinding")
    public Binding bindQueueExchange(@Qualifier("bootQosQueue") Queue queue, @Qualifier("bootQosExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.*.*").noargs();
    }

}
