package com.llmcu.rabbitmq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/8 17:25
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq-consumer.xml");
    }
}
