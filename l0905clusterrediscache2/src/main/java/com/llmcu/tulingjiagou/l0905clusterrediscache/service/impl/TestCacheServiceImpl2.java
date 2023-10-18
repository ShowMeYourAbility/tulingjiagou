package com.llmcu.tulingjiagou.l0905clusterrediscache.service.impl;

import com.llmcu.tulingjiagou.l0905clusterrediscache.service.TestCacheService;
import com.llmcu.tulingjiagou.l0905clusterrediscache.service.TestCacheService2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "cache")
public class TestCacheServiceImpl2 implements TestCacheService2 {

    @Override
    @Cacheable( key = "#id", sync = true)
    public String sayHello(Integer id) {
        System.out.println("没找到缓存:hello" + id + "。。。。。。。。。。。");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" + id;
    }

    @Override
    public String sayHello1(Integer id) {
        System.out.println("没找到缓存:hello1" + id + "。。。。。。。。。。。");
        return sayHello(id);
    }

    @Override
    @CacheEvict( key = "#id")
    public String sayHello2(Integer id) {
        System.out.println("没找到缓存:hello2" + id + "。。。。。。。。。。。");
        return "hello" + id;
    }

    @Override
    @CachePut( key = "#id")
    public String sayHello3(Integer id) {
        System.out.println("没找到缓存:hello3" + id + "。。。。。。。。。。。");
        return "hello3" + id;
    }
}
