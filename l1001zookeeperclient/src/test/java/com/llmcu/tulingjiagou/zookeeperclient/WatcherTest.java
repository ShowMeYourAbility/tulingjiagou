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
public class WatcherTest {

    private static final String ZK_ADDRESS = "192.168.128.243:2181";

    private static final int SESSION_TIMEOUT = 5000;

    private static ZooKeeper zooKeeper;

    private static final String ZK_NODE = "/zk-node";

    private static CountDownLatch latch = new CountDownLatch(1);

    private static Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (Objects.equals(event.getType(), Watcher.Event.EventType.None)
                    && Objects.equals(event.getState(), Watcher.Event.KeeperState.SyncConnected)) {
                log.info("连接事件类型*****");
                log.info("事件路径为：{}",event.getPath());
                latch.countDown();
                log.info("连接成功");
            }else{
                log.info("非连接事件类型....");
                log.info("事件类型为：{}",event.getType());
                log.info("事件路径为：{}",event.getPath());
                log.info("事件状态为：{}",event.getState());
                byte[] data = new byte[0];
                try {
                    data = zooKeeper.getData(ZK_NODE, true, null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("连接后立马查询后的数据为:{}",new String(data));
            }
        }
    };


    @BeforeClass
    public static void init() throws IOException, InterruptedException {

        zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIMEOUT, watcher);
        log.info("开始连接");
        latch.await();

    }

    @Before
    public void before() {
        log.info("before....");
    }

    @Test
    public void method1() throws KeeperException, InterruptedException {

        byte[] data = zooKeeper.getData(ZK_NODE, true, null);
        log.info("连接后立马查询后的数据为:{}",new String(data));

    }



    @After
    public void after() {
        log.info("after....");
    }


    @AfterClass
    public static void afterClass() throws InterruptedException {
        TimeUnit.SECONDS.sleep(120);
        log.info("afterClass....");
    }

}