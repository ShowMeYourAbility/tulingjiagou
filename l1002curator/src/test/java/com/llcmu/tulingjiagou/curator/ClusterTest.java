package com.llcmu.tulingjiagou.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ClusterTest extends ClusterBaseTest{

    @Test
    public void testMultiIp() throws Exception {
        for(int i=0; i<30;i++){
            String path = getClient()
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/curator-node/some-node", "some-data".getBytes());
            log.info("curator创建节点 :{} 成功", path);
            TimeUnit.SECONDS.sleep(2);

            getClient().delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator-node/some-node");
            log.info("curator删节点 :{} 成功", path);
            TimeUnit.SECONDS.sleep(5);
        }

    }

    @Test
    public void testWithProtection() throws Exception {
        for(int i=0; i<30;i++){
            String path = getClient()
                    .create()
                    .creatingParentsIfNeeded()
                    .withProtection()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath("/curator-node/temp/someESnode", "some-data".getBytes());
            log.info("curator创建节点 :{} 成功", path);


            path = getClient()
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath("/curator-node/temp2/someESnode", "some-data".getBytes());
            log.info("curator创建节点 :{} 成功", path);
            TimeUnit.SECONDS.sleep(2);


        }

    }

}
