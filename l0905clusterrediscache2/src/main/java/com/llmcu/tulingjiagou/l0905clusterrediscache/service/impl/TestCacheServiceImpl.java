package com.llmcu.tulingjiagou.l0905clusterrediscache.service.impl;

import com.llmcu.common.util.DateUtils;
import com.llmcu.tulingjiagou.l0905clusterrediscache.dao.NormalMapper;
import com.llmcu.tulingjiagou.l0905clusterrediscache.pojo.Normal;
import com.llmcu.tulingjiagou.l0905clusterrediscache.service.TestCacheService;
import com.llmcu.tulingjiagou.l0905clusterrediscache.service.TestCacheService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "cache")
public class TestCacheServiceImpl implements TestCacheService {

    @Autowired
    private TestCacheService2 service2;

    @Autowired
    private NormalMapper normalMapper;

    public volatile Map<Integer,String> container = new HashMap<>();

    /**
     * null值测试
     * @param id
     * @return
     */
    @Override
    @Cacheable( key = "#id", sync = true)
    @Caching
    public String sayHello(Integer id) {
        System.out.println("没找到缓存:hello" + id + "。。。。。。。。。。。");
        if(id>10){
            return null;
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" + id;
    }

    /**
     * 与上面的方法对比，cacheNames和key完全相同，sync对两个方法都有效
     */
    @Override
    @Cacheable( key = "#id", sync = true)
    public String sayHello5(Integer id) {
        System.out.println("没找到缓存:hello5" + id + "。。。。。。。。。。。");
        if(id>10){
            return null;
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello5" + id;
    }

    /**
     * 和@Transactional一样，只有第一层有效，因为AOP原理
     */
    @Override
    public String sayHello1(Integer id) {
        System.out.println("没找到缓存:hello1" + id + "。。。。。。。。。。。");
//        return service2.sayHello(id);
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

    /**
     * 在@Cacheable中的cacheNames可以覆盖@CacheConfig中的cacheNames
     */
    @Override
    @Cacheable(cacheNames="helloCache",  key = "#id", sync = true)
    public String sayHello4(Integer id) {
        System.out.println("没找到缓存:hello4" + id + "。。。。。。。。。。。");
        return "hello" + id;
    }

    @Override
    @CacheEvict( key = "#id")
    public String updateAndClear(Integer id) {
        String result = "updateAndClear:" + id;
        container.put(id,result);
        container.forEach((key,value)->{
            System.out.println("执行update后，还有数据："+value);
        });
        System.out.println("container.size():" + container.size());
        return result;
    }

    @Override
    @CacheEvict( key = "#id")
    public String addAndClear(Integer id) {
        String result = "addAndClear"+id;
        container.put(id,result);
        return result;
    }

    @Override
    @Cacheable( key = "#id", sync = true)
    public String queryAndCache(Integer id) {
        String result = container.get(id);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @CachePut( key = "#id")
    public String addAndCache(Integer id) {
        String result = "addAndClear"+id;
        container.put(id,result);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * id为偶数时，先执行
     */
    @Override
    @CachePut( key = "#id")
    public String updateAndCache(Integer id) {
        Normal normal = normalMapper.selectByPrimaryKey(1l).get();
        if(id%2==1){
            normal.setCname("a");
            normalMapper.updateByPrimaryKey(normal);
        }else{
            normal.setCname("b");
            normalMapper.updateByPrimaryKey(normal);
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return normal.getCname();
    }

    @Override
    public String queryMap(Integer id) {
        long l = Long.parseLong(String.valueOf(id));
        return normalMapper.selectByPrimaryKey(l).map(x->x.getCname()).orElse("nil");
    }
}
