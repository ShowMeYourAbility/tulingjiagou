package com.llmcu.tulingjiagou.zookeeperclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CommonTest {

    private static final String ZK_ADDRESS = "192.168.128.243:2181";

    private static final int SESSION_TIMEOUT = 5000;

    private static ZooKeeper zooKeeper;

    private static final String ZK_NODE = "/zk-node";


    @BeforeClass
    public static void init() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIMEOUT, event -> {
            if (Objects.equals(event.getType(), Watcher.Event.EventType.None)
                    && Objects.equals(event.getState(), Watcher.Event.KeeperState.SyncConnected)) {
                latch.countDown();
                log.info("连接成功");
            }
        });
        log.info("开始连接");
        latch.await();

    }

    @Before
    public void before() {
        log.info("before....");
    }

    @Test
    public void method1() throws KeeperException, InterruptedException {
        // 同步创建节点
        String path = zooKeeper.create(ZK_NODE + "/" + System.currentTimeMillis(), "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("同步创建节点:{}成功", path);
        // 异步创建节点
        zooKeeper.create(ZK_NODE + "/sync" + System.currentTimeMillis(), "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, (int rc, String path2, Object ctx, String name) -> {
            log.info("异步创建节点返回rc:{}", rc);
            log.info("异步创建节点返回path2:{}", path2);
            log.info("异步创建节点返回ctx:{}", ctx);
            log.info("异步创建节点返回name:{}", name);
        }, "context");
        // 修改节点
        Stat stat = new Stat();
        zooKeeper.getData(ZK_NODE, false, stat);
        Stat newStat = zooKeeper.setData(ZK_NODE, "NewData".getBytes(), stat.getVersion());
        byte[] newData = zooKeeper.getData(ZK_NODE, false, newStat);
        log.info("修改后的节点数据为：{}", new String(newData));

    }



    @After
    public void after() {
        log.info("after....");
    }


    @AfterClass
    public static void afterClass() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
        log.info("afterClass....");
    }

}