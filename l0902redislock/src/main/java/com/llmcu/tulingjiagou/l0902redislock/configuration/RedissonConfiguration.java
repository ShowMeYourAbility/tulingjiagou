package com.llmcu.tulingjiagou.l0902redislock.configuration;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Configuration
public class RedissonConfiguration {
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        String addr = "192.168.128.243:8001,192.168.128.245:8002,192.168.128.246:8003,192.168.128.243:8004,192.168.128.245:8005,192.168.128.246:8006";
        String[] addrArray = StringUtils.commaDelimitedListToStringArray(addr);
        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
        for (int i = 0; i < addrArray.length; i++) {
            addrArray[i] = "redis://" + addrArray[i];
        }
        Config config = new Config();
        config.useClusterServers().addNodeAddress(addrArray).setPassword("zhuge");
        return Redisson.create(config);
    }

    @Bean("productBloomFilter")
    public RBloomFilter rBloomFilter(RedissonClient redisson) throws InterruptedException {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("productBloomFilter");
        boolean exists = bloomFilter.isExists();
        if(exists){
            bloomFilter.delete();
            System.out.println("开始删除bloomFilter");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("成功删除bloomFilter");
        }
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add("cache:product:5");
        bloomFilter.add("cache:product:2");
        bloomFilter.add("cache:product:1");
        bloomFilter.add("cache:product:4");
        bloomFilter.add("cache:product:33");
        return bloomFilter;
    }
}
