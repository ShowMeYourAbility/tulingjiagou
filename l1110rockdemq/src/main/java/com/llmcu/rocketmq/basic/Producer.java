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

import com.llmcu.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

//简单样例：同步发送消息
@Slf4j
public class Producer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer("BasicProducerGroupName");
        producer.setNamesrvAddr("192.168.128.243:9876;192.168.128.245:9876;192.168.128.246:9876");
        producer.start();

        for (int i = 0; i < 30; i++) {
            try {
                String content = DateUtils.getCurrentDateTime() + ":Hello world" + i;
                Message msg = new Message("BasicTopic",
                        "TagA",
                        "OrderID188",
                        content.getBytes(RemotingHelper.DEFAULT_CHARSET));
                //同步传递消息，消息会发给集群中的一个Broker节点。
                    SendResult sendResult = producer.send(msg);
//                producer.sendOneway(msg);
                log.info("发送消息{}成功", content);
            } catch (Exception e) {
                log.info("发送消息发生异常{}", e);
            }
        }
        log.info("生产者开始关闭==============================");
        producer.shutdown();
        log.info("生产者关闭成功==============================");
    }
}
