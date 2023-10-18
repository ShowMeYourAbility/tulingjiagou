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
package com.llmcu.rocketmq.batch;

import com.llmcu.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//简单样例：同步发送消息
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("BatchProducerGroupName");
        producer.setNamesrvAddr("192.168.128.243:9876;192.168.128.245:9876;192.168.128.246:9876");
        producer.start();

        List<Message> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String content = DateUtils.getCurrentDateTime() + ":Hello world" + i;
            Message msg = new Message("BatchTopic",
                    "TagA",
                    "OrderID188",
                    content.getBytes(RemotingHelper.DEFAULT_CHARSET));
            list.add(msg);
            log.info("发送消息{}成功", content);
        }
        SendResult sendResult = producer.send(list);
        log.info("生产者开始关闭==============================");
        producer.shutdown();
        log.info("生产者关闭成功==============================");
    }

    public static List<List<Message>>  splitMsg(List<Message> msgList){
        List<List<Message>> result = new ArrayList<>();
        for (Message message : msgList) {
            if(message==null){
                result.add(Arrays.asList(message));
            }else{

            }
        }
        return result;
    }
}
