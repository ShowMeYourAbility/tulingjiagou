package com.llmcu.tulingjiagou.l0903singlerediscache.service.impl;

import com.llmcu.tulingjiagou.l0903singlerediscache.service.TestCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestCacheServiceImpl implements TestCacheService {
    @Cacheable(value="sayHello",key = "#id")
    @Override
    public String sayHello(Integer id) {
        System.out.println("sayHello");
        return "hello";
    }
}
