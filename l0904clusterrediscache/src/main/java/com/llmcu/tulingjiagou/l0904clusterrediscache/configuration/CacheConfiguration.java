package com.llmcu.tulingjiagou.l0904clusterrediscache.configuration;

import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheConfiguration {

    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson (){
        String addr = "192.168.128.243:8001,192.168.128.245:8002,192.168.128.246:8003,192.168.128.243:8004,192.168.128.245:8005,192.168.128.246:8006";
        String[] addrArray = StringUtils.commaDelimitedListToStringArray(addr);
        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
        for(int i = 0; i < addrArray.length; i++){
            addrArray[i]="redis://"+addrArray[i];
        }
        Config config = new Config();
        config.useClusterServers().addNodeAddress(addrArray).setPassword("zhuge");
        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redisson){

        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
//        config.put("testMap", new CacheConfig(24*60*1000, 12*60*1000));
        // 60s 和 10s(10S后key即删除)
        config.put("testMap", new CacheConfig(10*60*1000, 1*60*1000));
        RedissonSpringCacheManager redisCacheManager = new RedissonSpringCacheManager(redisson,config);
        return redisCacheManager;
    }
}
