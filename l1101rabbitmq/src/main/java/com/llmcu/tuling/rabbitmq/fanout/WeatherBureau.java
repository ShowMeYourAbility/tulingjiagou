package com.llmcu.tuling.rabbitmq.fanout;

import com.llmcu.tuling.rabbitmq.helloworld.utils.RabbitConstant;
import com.llmcu.tuling.rabbitmq.helloworld.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/7 16:06
 */
public class WeatherBureau {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = connection.createChannel();
//开启confirm监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //第二个参数代表接收的数据是否为批量接收，一般我们用不到。
                System.out.println("消息已被Broker接收,Tag:" + l );
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息已被Broker拒收,Tag:" + l);
            }
        });
        channel.addReturnListener(r -> {
            System.err.println("===========================");
            System.err.println("Return编码：" + r.getReplyCode() + "-Return描述:" + r.getReplyText());
            System.err.println("交换机:" + r.getExchange() + "-路由key:" + r.getRoutingKey() );
            System.err.println("Return主题：" + new String(r.getBody()));
            System.err.println("===========================");
        });
        //第一个参数交换机名字   其他参数和之前的一样
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"",true , null , input.getBytes());

//        channel.close();
//        connection.close();
    }
}
