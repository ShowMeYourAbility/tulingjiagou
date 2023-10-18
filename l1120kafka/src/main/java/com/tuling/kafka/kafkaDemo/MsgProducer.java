package com.tuling.kafka.kafkaDemo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MsgProducer {
    private final static String TOPIC_NAME = "my-replicated-topic3";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.128.243:9092,192.168.128.245:9092,192.168.128.246:9092");
        // 生产者的幂等性
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
         /*
         ==========================================1:ack应答===============================================
         发出消息持久化机制参数
        （1）acks=0： 表示producer不需要等待任何broker确认收到消息的回复，就可以继续发送下一条消息。性能最高，但是最容易丢消息。
        （2）acks=1： 至少要等待leader已经成功将数据写入本地log，但是不需要等待所有follower是否成功写入。就可以继续发送下一
             条消息。这种情况下，如果follower没有成功备份数据，而此时leader又挂掉，则消息会丢失。
        （3）acks=-1或all： 需要等待 min.insync.replicas(默认为1，推荐配置大于等于2（默认1其实和ack=1效果一样，因为此参个数leader也会统计其中）) 这个参数配置的副本个数都成功写入日志，这种策略会保证
            只要有一个备份存活就不会丢失数据。这是最强的数据保证。一般除非是金融级别，或跟钱打交道的场景才会使用这种配置。
         */
//        props.put(ProducerConfig.ACKS_CONFIG, "1");
        /*
        ==========================================2：失败重试===============================================
        发送失败会重试，默认重试间隔100ms，重试能保证消息发送的可靠性，但是也可能造成消息重复发送，比如网络抖动，所以需要在
        接收者那边做好消息接收的幂等性处理
        */

        //生产者发送失败后的重试次数，默认0
//        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        //重试间隔设置
//        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);
        /*
        ==========================================3：发送到缓冲区的速度大于传输到server的速度，消息就会在缓冲区待着。这个缓冲区大小限制===============================================
        重要性：高
        类型：Long
        默认值：33554432字节，即32M
        producer可以用于缓存等待发送到服务端的消息记录的缓冲区大小，当消息记录发送到缓冲区的速度大于传输到server的速度，那么等待发送的消息记录将会放在缓冲区，缓冲区如果满了，那么producer会阻塞max.block.ms指定的毫秒数，超过该毫秒数时，将抛出异常。
        注意：该缓冲区的大小设置与整个producer需要使用到的producer大体一致，但是要注意并不是所有的缓冲区都是用来存放待发送的records的，比如还有一部分用于压缩数据（当压缩数据的选项开启），还有一部分用于维护in-flight（正在发送）的请求列表。
        */
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        /*
        ==========================================4：默认0ms意味着有消息就发，一般设置10ms，这样就会造成一批数据一起发送。可这批数据大小也要被限制===============================================
        ==========================================如果设置时间，且ack=1的话，放入缓存的线程和发送的线程肯定不会是同一个（因为发送只要一个线程，放入缓存是多个）放入缓存的各个线程怎么知道它的数据被接收了呢？？===============================================
        kafka本地线程会从缓冲区取数据，批量发送到broker，
        设置批量发送消息的大小，默认值是16384，即16kb，就是说一个batch满了16kb就发送出去
        */
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        /*
        默认值是0，意思就是消息必须立即被发送，但这样会影响性能
        一般设置10毫秒左右，就是说这个消息发送完后会进入本地的一个batch，如果10毫秒内，这个batch满了16kb就会随batch一起被发送出去
        如果10毫秒内，batch没满，那么也必须把消息发送出去，不能让消息的发送延迟时间太长
        */
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);

        //把发送的key从字符串序列化为字节数组
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //把发送消息value从字符串序列化为字节数组
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String, String> producer = new KafkaProducer<>(props);

        int msgNum = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(msgNum);
        for (int i = 1; i <= msgNum; i++) {
            log.info("开始发送消息=======================================");
            Order order = new Order(i, 100 + i, 1, 1000.00);
            //指定发送分区
            /*ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC_NAME
                    , 1, order.getOrderId().toString(), JSON.toJSONString(order));*/
            //未指定发送分区，具体发送的分区计算公式：hash(key)%partitionNum
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC_NAME
                    , order.getOrderId().toString(), JSON.toJSONString(order));

            //等待消息发送成功的同步阻塞方法
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println("同步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-"
                    + metadata.partition() + "|offset-" + metadata.offset());

            //异步回调方式发送消息
            /*producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        log.info("orderid为{}的信息发送失败",order.getOrderId());
                        System.err.println("发送消息失败：" + exception.getStackTrace());

                    }
                    if (metadata != null) {
                        log.info("orderid为{}的信息发送成功",order.getOrderId());
                        System.out.println("异步方式发送消息结果：" + "topic-" + metadata.topic() + "|partition-"
                                + metadata.partition() + "|offset-" + metadata.offset());
                    }
                    countDownLatch.countDown();
                }
            });*/

            //送积分 TODO

        }

        countDownLatch.await(12, TimeUnit.SECONDS);
        producer.close();
    }
}