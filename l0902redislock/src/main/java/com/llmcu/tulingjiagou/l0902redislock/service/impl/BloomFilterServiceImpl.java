package com.llmcu.tulingjiagou.l0902redislock.service.impl;

import com.llmcu.tulingjiagou.l0902redislock.service.BloomFilterService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
public class BloomFilterServiceImpl implements BloomFilterService {

    private final RedissonClient redisson;

    public BloomFilterServiceImpl(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public String queryProduct(Integer id) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("productBloomFilter");
        if (!bloomFilter.contains("cache:product:" + id)) {
            return null;
        }
        return "hello" + id;
    }

    @Override
    public void addProduct(Integer id) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("productBloomFilter");
        bloomFilter.add("cache:product:" + id);
    }
}
