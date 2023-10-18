package com.llcmu.tulingjiagou.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

@Slf4j
public class CommonTest extends BaseTest{
    @Test
    public void testDataUpdataAndQuery() throws Exception {

        Stat stat = new Stat();
        // 4、"查"1：查询节点数据
        byte[] bytes = getClient().getData().storingStatIn(stat).forPath("/curator-node/myson");
        log.info("第一次存储的数据为：{}",new String(bytes));
        // 3、”改“：修改数据
        stat = getClient().setData().withVersion(stat.getVersion()).forPath("/curator-node/myson", "changed!".getBytes());
        bytes = getClient().getData().storingStatIn(stat).forPath("/curator-node/myson");
        log.info("第一次存储的数据修改为：{}",new String(bytes));

        // 4、”查“补：“无数据查询为ip地址（其实必有数据）”
        byte[] bytes2 = getClient().getData().forPath("/curator-node1");
        log.info("第二次存储的数据为：{}",new String(bytes2));

        // 4、”查“2：异步查
        getClient().getData().inBackground((client,event)->{
            log.info("异步查询到的数据为：{}", new String(event.getData()));
            log.info("异步查询的事件类型为 ：{}",event.getType());
        }).forPath("/curator-node/myson");

    }

    @Test
    public void testQueryChildrenNodes() throws Exception {
        // 4、”查“3：子节点查询
        List<String> childrenNode = getClient().getChildren().forPath("/curator-node");
        log.info("");
        childrenNode.forEach(System.out::println);
    }

}
