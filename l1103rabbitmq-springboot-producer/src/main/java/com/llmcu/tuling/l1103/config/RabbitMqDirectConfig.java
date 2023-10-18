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
public class RabbitMqDirectConfig {

    //定义交换机的名字
    public static final String  EXCHANGE_NAME = "boot_direct_exchange";
    //定义队列的名字
    public static final String QUEUE_NAME = "boot_direct_queue";

    //1、声明交换机
    @Bean("bootDirectExchange")
    public Exchange bootExchange(){

        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).build();
    }



    //2、声明队列
    @Bean("bootDirectQueue")
    public Queue bootQueue(){

        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    //3、队列与交换机进行绑定
    @Bean("bootDirectBinding")
    public Binding bindQueueExchange(@Qualifier("bootDirectQueue") Queue queue, @Qualifier("bootDirectExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("info").noargs();
    }

}
