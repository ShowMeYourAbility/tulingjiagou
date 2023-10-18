package com.llmcu.tulingjiagou.l0905clusterrediscache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface TestCacheService {
    String sayHello(Integer id);

    String sayHello2(Integer id);

    String sayHello3(Integer id);

    @Cacheable( key = "#id", sync = true)
    String addAndClear(Integer id);

    @Cacheable( key = "#id", sync = true)
    String sayHello5(Integer id);

    String sayHello1(Integer id);

    @Cacheable( key = "#id", sync = true)
    String sayHello4(Integer id);

    @CachePut( key = "#id")
    String updateAndClear(Integer id);

    @Cacheable( key = "#id", sync = true)
    String queryAndCache(Integer id);

    @CacheEvict( key = "#id")
    String addAndCache(Integer id);

    @CacheEvict( key = "#id")
    String updateAndCache(Integer id);

    @Cacheable( key = "#id", sync = true)
    String queryMap(Integer id);
}
