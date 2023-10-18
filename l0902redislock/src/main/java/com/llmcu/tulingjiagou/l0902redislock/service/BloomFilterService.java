package com.llmcu.tulingjiagou.l0902redislock.service;

public interface BloomFilterService {
    String queryProduct(Integer id);

    void addProduct(Integer id);
}
