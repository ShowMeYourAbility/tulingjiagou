package com.llmcu.rocketmq.springboot.controller;

import com.llmcu.common.dto.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/31 17:34
 */
@RestController
@RequestMapping("mq")
@Slf4j
public class HelloController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("sendMsg")
    public ResponseJson<Boolean> sendMsg(@RequestParam String msg){
        String topic = "hello_mq_from_springboot";
        rocketMQTemplate.convertAndSend(topic,msg);
        return ResponseJson.success(true);
    }
    @GetMapping("sendMsgInTransaction")
    public ResponseJson<Boolean> sendMsgInTransaction(@RequestParam String msg){
        String topic = "hello_mq_from_springboot";
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            //尝试在Header中加入一些自定义的属性。
            Message<String> message = MessageBuilder.withPayload(msg)
                    .setHeader(RocketMQHeaders.TRANSACTION_ID,"TransID_"+i)
                    //发到事务监听器里后，这个自己设定的TAGS属性会丢失。但是上面那个属性不会丢失。
                    .setHeader(RocketMQHeaders.TAGS,tags[i % tags.length])
                    //MyProp在事务监听器里也能拿到，为什么就单单这个RocketMQHeaders.TAGS拿不到？这只能去调源码了。
                    .setHeader("MyProp","MyProp_"+i)
                    .build();
            String destination =topic+":"+tags[i % tags.length];
            //这里发送事务消息时，还是会转换成RocketMQ的Message对象，再调用RocketMQ的API完成事务消息机制。
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message,destination);
            log.info("sendResult:{}",sendResult);
        }
        return ResponseJson.success(true);
    }


}
