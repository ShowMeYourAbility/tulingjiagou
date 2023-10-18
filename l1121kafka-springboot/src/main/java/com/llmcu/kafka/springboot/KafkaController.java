package com.llmcu.kafka.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private final static String TOPIC_NAME = "my-replicated-topic3";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private long key = 0;

    @RequestMapping("/send")
    public void send() {
        int times = 0;
        while (times++ < 5) {
//        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg");
            kafkaTemplate.send(TOPIC_NAME, String.valueOf(++key), "this is a msg"+key);
        }

    }
}
