package com.llmcu.tulingjiagou.l09redis.c01jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.*;

public class JedisSingleTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        // timeout，这里既是连接超时又是读写超时，从Jedis 2.8开始有区分connectionTimeout和soTimeout的构造函数
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.128.243", 6379, 3000, null);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.128.243", 6381, 3000, null);
        //从redis连接池里拿出一个连接执行命令
        Jedis jedis = jedisPool.getResource();
        //******* jedis普通操作示例 ********
        normalOperate(jedis);
//        pipelineOperate(jedis);
        luaOperate(jedis);
//        luaOperate2(jedis);
        jedis.close();
    }

    /**
     * b==0 编译错误，luae脚本全部不执行
     * @param jedis
     */
    private static void luaOperate2(Jedis jedis) {
        jedis.set("age","36");
        String script = "redis.call('set','age',35) " +
                "redis.call('setbit',KEYS[1],KEYS[2],ARGV[1]) " +
                "local b == 0 " +
                "redis.call('set','name','luaname')";

        System.out.println("LUA:"+jedis.eval(script, Arrays.asList("luaBitMap","2"),Arrays.asList("1")));
    }

    // 作为一个整体去执行，第3个命令报错，但前2个命令已执行不会回滚
    private static void luaOperate(Jedis jedis) {
        jedis.set("age","36");
        String script = "redis.call('set','age',35) " +
                "redis.call('setbit',KEYS[1],KEYS[2],ARGV[1]) " +
                "redis.call('setbit','luaBitMap2','-1','1') " +
                "redis.call('set','name','luaname')";

        System.out.println("LUA:"+jedis.eval(script, Arrays.asList("luaBitMap","2"),Arrays.asList("1")));
    }

    private static void pipelineOperate(Jedis jedis) {
        Pipeline pipeline = jedis.pipelined();
        for(int i = 0 ; i < 10 ; i++){
            pipeline.incr("pipelineCnt");
            pipeline.set("name"+i,"liuling"+i);
            // 模拟管道报错
            pipeline.setbit("zhuge",i-5,true);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        System.out.println(results);
        // clear data
        for(int i = 0 ; i < 10 ; i++){
            pipeline.del("pipelineCnt");
            pipeline.del("name"+i);
            pipeline.del("zhuge");
        }
        results = pipeline.syncAndReturnAll();
        System.out.println(results);
    }

    public static void normalOperate(Jedis jedis) {
        // string
        System.out.println(jedis.set("name", "liuling"));
        System.out.println("name:" + jedis.get("name"));
        jedis.del("name");
        // hash
        Map<String, String> goodsCntMap = new HashMap<>();
        goodsCntMap.put("1001", "2");
        goodsCntMap.put("1002", "3");
        goodsCntMap.put("1003", "1");
        // c:cart(购物车);g:goods(商品);cnt
        jedis.hmset("433c:g:cnt", goodsCntMap);
        System.out.println("433c:g:cnt:1002:" + jedis.hget("433c:g:cnt", "1002"));
        jedis.del("433c:g:cnt");
        // list
        jedis.lpush("orderlist", "1", "2", "3");
        String popResult;
        while ((popResult = jedis.lpop("orderlist")) != null) {
            System.out.println("popResult:" + popResult);
        }
        System.out.println("popResult finally:"+popResult);
        jedis.del("orderlist");
    }
}
