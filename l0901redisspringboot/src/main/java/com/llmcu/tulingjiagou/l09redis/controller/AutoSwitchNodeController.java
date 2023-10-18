package com.llmcu.tulingjiagou.l09redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class AutoSwitchNodeController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("redis/autoSwitchNode")
    public String autoSwitchNode() {
        String name;
        for (int i = 0; i < 10000; i++) {
            try {
                redisTemplate.opsForValue().set("name" + i, "liuling" + i);
                name = redisTemplate.opsForValue().get("name" + i);
                System.out.println("成功设置name值：" + name);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @GetMapping("redis/testCluster")
    public String testCluster() {
        redisTemplate.opsForValue().set("clusterName" , "clusterLiuling" );
        String name = redisTemplate.opsForValue().get("clusterName" );
        System.out.println("成功设置name值：" + name);
        Boolean success = redisTemplate.opsForValue().setIfAbsent("name", "liuling", 10, TimeUnit.SECONDS);
        System.out.println("success:"+success);
//        redisTemplate.delete("clusterName");
        return "ok";
    }

}
