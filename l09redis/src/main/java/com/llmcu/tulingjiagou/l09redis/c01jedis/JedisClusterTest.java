package com.llmcu.tulingjiagou.l09redis.c01jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisClusterTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.128.243",8001));
        jedisClusterNode.add(new HostAndPort("192.168.128.243",8004));
        jedisClusterNode.add(new HostAndPort("192.168.128.245",8002));
        jedisClusterNode.add(new HostAndPort("192.168.128.245",8005));
        jedisClusterNode.add(new HostAndPort("192.168.128.246",8003));
        jedisClusterNode.add(new HostAndPort("192.168.128.246",8006));


        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode,6000, 5000, 10, "zhuge", jedisPoolConfig);

        System.out.println(jedisCluster.set("name", "liuling"));
        System.out.println("name:" + jedisCluster.get("name"));
        jedisCluster.del("name");

        jedisCluster.close();
//        try {
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
