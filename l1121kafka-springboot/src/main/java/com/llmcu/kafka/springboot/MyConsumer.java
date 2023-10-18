package com.llmcu.kafka.springboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyConsumer {

    /**
     * @KafkaListener(groupId = "testGroup", topicPartitions = {
     *             @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     *             @TopicPartition(topic = "topic2", partitions = "0",
     *                     partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     *     },concurrency = "6")
     *  //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     * @param record
     */
    @KafkaListener(topics = "my-replicated-topic3",groupId = "springbootGroup")
    public void listenZhugeGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        log.info("value:{}",value);
        log.info("record:{}",record);
        //手动提交offset
        ack.acknowledge();
    }

    //@KafkaListener都是一条一条消费。@KafkaListener支持批量消费，只需要设置batchListener参数为true。搜“kafkalistener 批量消费”（需要自定义factoryBean,并为kafkaListener指定这个factory）
    /*@KafkaListener(topics = "my-replicated-topic3",groupId = "springbootGroup",properties = {"max.poll.records=3"})
    public void listenTulingGroup(List<ConsumerRecord<String, String>> list, Acknowledgment ack) {
        for (ConsumerRecord<String, String> record : list) {
            String value = record.value();
            log.info("value:{}",value);
            log.info("record:{}",record);
        }
        ack.acknowledge();
    }*/
}
