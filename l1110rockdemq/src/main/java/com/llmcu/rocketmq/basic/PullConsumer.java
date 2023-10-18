/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.llmcu.rocketmq.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class PullConsumer {
    private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<>();

    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("please_rename_unique_group_name_5");
        consumer.setNamesrvAddr("192.168.128.243:9876;192.168.128.245:9876;192.168.128.246:9876");
        consumer.start();

        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTest");
        for (MessageQueue mq : mqs) {
            log.info("Consume from the queue:{}",mq);
            SINGLE_MQ:
            while (true) {
                log.info("into while..........");
                try {
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
//                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, "*", getMessageQueueOffset(mq), 32);
                    Optional.ofNullable(pullResult.getMsgFoundList()).ifPresent(list->{
                        String batchResult = pullResult.getMsgFoundList().stream().map(MessageExt::getBody).map(String::new).collect(Collectors.joining("\n"));
                        log.info("batchResult:{}",batchResult);
                    });
                    log.info("pullResult.getNextBeginOffset():{}",pullResult.getNextBeginOffset());
                    log.info("pullResult.getMaxOffset():{}",pullResult.getMaxOffset());
                    log.info("getMessageQueueOffset(mq):{}",getMessageQueueOffset(mq));

                    if(Objects.equals(pullResult.getMaxOffset(),pullResult.getNextBeginOffset())){
                        break SINGLE_MQ;
                    }
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            log.info("FOUND-------------------------");
                            break;
                        case NO_MATCHED_MSG:
                            log.info("NO_MATCHED_MSG=======================");
                            break;
                        case NO_NEW_MSG:
                            log.info("NO_NEW_MSG+++++++++++++++++++++++++");
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            log.info("OFFSET_ILLEGAL********************");
                            break;
                        default:
                            log.info("default////////////////////////////");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("out of while..........");
        }
        log.info("shutdown========================");
        consumer.shutdown();
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSE_TABLE.get(mq);
        if (offset != null){
            return offset;
        }

        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        OFFSE_TABLE.put(mq, offset);
    }

}
