package com.tuling.kafka.kafkaDemo.delayQueue;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

@Slf4j
public class MsgConsumer {
    private final static String TOPIC_NAME = "oneMinuteDelayTopic";
    private final static String CONSUMER_GROUP_NAME = "javaConsumeGroup";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.128.243:9092,192.168.128.245:9092,192.168.128.246:9092");
        // 消费分组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        /*
        当消费主题的是一个新的消费组，或者指定offset的消费方式，offset不存在，那么应该如何消费
        latest(默认) ：只消费自己启动之后发送到主题的消息
        earliest：第一次从头开始消费，以后按照消费offset记录继续消费，这个需要区别于consumer.seekToBeginning(每次都从头开始消费)
        */
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10 * 1000);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

//        consumer.subscribe(Arrays.asList(TOPIC_NAME));


        //指定offset消费
        consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME, 0)));


        Long offset = 0L;
        while (true) {

            long position = consumer.position(new TopicPartition(TOPIC_NAME, 0));
            log.info("position:{}", position);
            Map<TopicPartition, Long> topicPartitionLongMap = consumer.beginningOffsets(Collections.singleton(new TopicPartition(TOPIC_NAME, 0)));
            topicPartitionLongMap.forEach((topic, pos) -> {
                log.info("beginningOffsets:{}-{}", topic.partition(), pos);
            });

            consumer.seek(new TopicPartition(TOPIC_NAME, 0), position);

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            boolean commit = true;
            for (ConsumerRecord<String, String> record : records) {
                commit = Objects.equals(record.key(),"5")?false:true;
                log.info("收到消息：partition = {},offset = {}, key = {}, value = {}",record.partition(), record.offset(), record.key(), record.value());
            }
            if(!commit){
                consumer.seek(new TopicPartition(TOPIC_NAME, 0), position-1);
                consumer.commitSync();
            }
            if (records.count() > 0 && commit) {
                consumer.commitSync();
            }
            offset++;
        }
    }

    /*public static long getLastOffset(SimpleConsumer consumer, String topic, int partition,

                                     long whichTime, String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);

        Map requestInfoMap = new HashMap();

        requestInfoMap.put(topicAndPartition, new PartitionOffsetRequestInfo(kafka.api.OffsetRequest.LatestTime(), 100));

        OffsetRequest request = new OffsetRequest( requestInfoMap, kafka.api.OffsetRequest.CurrentVersion() , clientName);

        OffsetResponse response = consumer.getOffsetsBefore(request);

        long[] validOffsets = response.offsets(topic, partition);

        for (long validOffset : validOffsets) {
            System.out.println(validOffset + " : ");

        }

        long largestOffset = validOffsets[0];

        long smallestOffset = validOffsets[validOffsets.length - 1];

        System.out.println(smallestOffset + " : " + largestOffset );

        return largestOffset;

    }*/
}