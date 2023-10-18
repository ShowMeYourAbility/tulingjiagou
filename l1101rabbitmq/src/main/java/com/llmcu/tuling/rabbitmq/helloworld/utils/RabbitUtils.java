package com.llmcu.tuling.rabbitmq.helloworld.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/6 11:22
 */
public class RabbitUtils {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    static {
        connectionFactory.setHost("192.168.128.243");
        connectionFactory.setPort(5672);//5672是RabbitMQ的默认端口号
        connectionFactory.setUsername("rabbitmq");
        connectionFactory.setPassword("rabbitmq");
        connectionFactory.setVirtualHost("/hello_rabbitmq");
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = connectionFactory.newConnection();
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
