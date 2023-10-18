package com.llcmu.tulingjiagou.curator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.AfterClass;
import org.junit.BeforeClass;

@Data
@Slf4j
public class BaseTest {

    private static CuratorFramework client;

    @BeforeClass
    public static void init() throws Exception {
        // 0、建立连接
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(20000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.128.243:2181")
                .sessionTimeoutMs(5 * 60 * 1000) // 会话超时时间
                .connectionTimeoutMs(30000) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace("base") // 包含隔离名称
                .build();
        client.start();
        // 1、“建”：创建节点
        String path = getClient()
                .create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curator-node/myson", "some-data".getBytes());
        log.info("curator创建节点 :{} 成功", path);
        path = getClient().create().forPath("/curator-node1");
        log.info("curator创建节点 :{} 成功", path);
        path = getClient().create().forPath("/curator-node/myson1");
        log.info("curator创建节点 :{} 成功", path);
        path = getClient().create().forPath("/curator-node/myson2");
        log.info("curator创建节点 :{} 成功", path);
        path = getClient().create().creatingParentsIfNeeded().forPath("/curator-node/myson2/mygrandson2");
        log.info("curator创建节点 :{} 成功", path);

    }

    @AfterClass
    public static void cleanData() throws Exception {
        // 2、“删”：删除节点
        getClient().delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator-node");
        getClient().delete().guaranteed().deletingChildrenIfNeeded().forPath("/curator-node1");
    }

    public static CuratorFramework getClient() {
        return client;
    }
}