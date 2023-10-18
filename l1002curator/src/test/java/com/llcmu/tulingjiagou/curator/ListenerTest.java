package com.llcmu.tulingjiagou.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ListenerTest extends BaseTest {

    //    @Test
    public void testNodeCache() throws InterruptedException {
        CuratorCache curatorCache = CuratorCache.build(getClient(), "/curator-node/myson");
        curatorCache.listenable().addListener((CuratorCacheListener.Type type, ChildData oldData, ChildData data) -> {
            System.out.println("--------------------------");
            //变化类型
            System.out.println(type);
            System.out.println("--------------------------");
            //变化前节点
            System.out.println(oldData);
            System.out.println(new String(oldData.getData()));
            System.out.println("--------------------------");
            //变化后节点
            System.out.println(data);
            System.out.println(new String(data.getData()));
            System.out.println("--------------------------");
        });
        curatorCache.start();
        TimeUnit.SECONDS.sleep(60 * 5);
    }

    //    @Test
    public void testPathCache() throws InterruptedException {
        CuratorCache curatorCache = CuratorCache.build(getClient(), "/curator-node");
        CuratorCacheListener curatorCacheListener = CuratorCacheListener
                .builder()
                .forPathChildrenCache("/curator-node", getClient(), (curatorFramework, pathChildrenCacheEvent) -> {
                    System.out.println("子节点变化了~");
                    //监听子节点的数据变更，并且拿到变更后的数据
                    //1.获取类型
                    PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                    System.out.println(type);
                    //2.判断类型是否是update
                    if (type.equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                        System.out.println("数据变了！！！");
                        byte[] data = pathChildrenCacheEvent.getData().getData();
                        System.out.println(new String(data));
                    }
                }).build();
        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();
        TimeUnit.SECONDS.sleep(60 * 5);
    }

    //    @Test
    public void testTreeCache() throws InterruptedException {
        CuratorCache curatorCache = CuratorCache.build(getClient(), "/");
        CuratorCacheListener curatorCacheListener = CuratorCacheListener
                .builder()
                .forTreeCache(getClient(), (curatorFramework, treeCacheEvent) -> {
                    System.out.println("节点变化了");
                    System.out.println(treeCacheEvent);
                    System.out.println(new String(treeCacheEvent.getData().getData()));
                }).build();
        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();
        TimeUnit.SECONDS.sleep(60 * 5);
    }

    @Test
    public void testNodeCache2() throws Exception {
        NodeCache nodeCache = new NodeCache(getClient(), "/curator-node/myson");
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                log.info("nodeChanged++++++++++++++++++++++++++++++");
                log.info("{} path nodeChanged: ", "base");
                log.info("path data:{}", new String(getClient().getData().forPath("/curator-node/myson")));
            }
        });
        //3. 开启监听.如果设置为true，则开启监听是，加载缓冲数据
        nodeCache.start(true);

        PathChildrenCache pathCache = new PathChildrenCache(getClient(), "/curator-node", true);
        pathCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                log.info("PathCache-------------------------------");
                log.info("eventType:{}", event.getType());
            }
        });
        pathCache.start(true);

        TreeCache treeCache = new TreeCache(getClient(), "/curator-node");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                log.info("TreeCache********************************");
                log.info("eventType:{}", event.getType());
            }
        });
        treeCache.start();

    }




    @AfterClass
    public static void cleanData() throws Exception {
        System.out.println("给你5分钟来测试：");
        TimeUnit.SECONDS.sleep(60*5);
        BaseTest.cleanData();
    }
}
