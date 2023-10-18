package com.llmcu.tulingjiagou.l09redis.c01jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class JedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        Set<String> set = new HashSet<>();
        set.add(new HostAndPort("192.168.128.243",26379).toString());
        set.add(new HostAndPort("192.168.128.243",26380).toString());
        set.add(new HostAndPort("192.168.128.243",26381).toString());
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster",set,jedisPoolConfig,3000,null);

        Jedis jedis = jedisSentinelPool.getResource();

        JedisSingleTest.normalOperate(jedis);

        jedis.close();
    }
}
