package com.llmcu.tuling.rabbitmq.helloworld;

import com.llmcu.tuling.rabbitmq.helloworld.utils.RabbitConstant;
import com.llmcu.tuling.rabbitmq.helloworld.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/5/6 11:21
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取TCP长连接
        Connection conn = RabbitUtils.getConnection();
        //创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = conn.createChannel();

        //创建队列,声明并创建一个队列，如果队列已存在，则使用这个队列
        //第一个参数：队列名称ID
        //第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
        //第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
        //第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
        //其他额外的参数, null
        channel.queueDeclare(RabbitConstant.QUEUE_HELLOWORLD,false, false, false, null);
//        channel.queueDeclare(RabbitConstant.QUEUE_BAIDU,false, false, false, null);

        String message = "hello白起666";

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

        //四个参数
        //exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
        //队列名称
        //额外的设置属性
        //最后一个参数是要传递的消息字节数组
        channel.basicPublish("", RabbitConstant.QUEUE_HELLOWORLD, null,message.getBytes());
//        channel.basicPublish("", RabbitConstant.QUEUE_BAIDU, null,message.getBytes());
//        channel.close();
//        conn.close();
        System.out.println("===发送成功===");

    }
}
