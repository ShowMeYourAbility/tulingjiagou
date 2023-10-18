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
public class RabbitMqTopicConfig {

    //定义交换机的名字
    public static final String  EXCHANGE_NAME = "boot_topic_exchange";
    //定义队列的名字
    public static final String QUEUE_NAME = "boot_topic_queue";

    //1、声明交换机
    @Bean("bootTopicExchange")
    public Exchange bootExchange(){

        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }



    //2、声明队列
    @Bean("bootTopicQueue")
    public Queue bootQueue(){

        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    //3、队列与交换机进行绑定
    @Bean("bootTopicBinding")
    public Binding bindQueueExchange(@Qualifier("bootTopicQueue") Queue queue, @Qualifier("bootTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
