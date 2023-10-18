package com.llmcu.tulingjiagou.l0902redislock.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisLock")
@Slf4j
public class LockController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/reduce")
    public String reduceStock() throws InterruptedException {

        reduce();
        return "ok";
    }

    private void reduce() throws InterruptedException {
        int stock = Integer.parseInt(Optional.ofNullable(redisTemplate.opsForValue().get("stock")).orElse("0"));
        if (stock > 0) {
            TimeUnit.MILLISECONDS.sleep(10);
            int newStock = stock - 1;
            redisTemplate.opsForValue().set("stock", Objects.toString(newStock));
            log.info("扣减后的库存为：{}",newStock);
        } else {
            log.info("没有库存可扣减");
        }
    }

    @GetMapping("/reduce2")
    public String reduceStock2() throws InterruptedException {
        String lockKey = "lock:product101";
        String lockValue = UUID.randomUUID().toString();
        Boolean lockValueSet = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 10, TimeUnit.SECONDS);
        if (!lockValueSet) {
            log.info("没有拿到锁");
            return "not ok";
        }
        try {
            reduce();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("开始删除锁");
            String realLockValue = redisTemplate.opsForValue().get(lockKey);
            if (Objects.equals(realLockValue, lockValue)) {
                redisTemplate.delete(lockKey);
            }
        }
        return "ok";
    }

    @GetMapping("/reduce3")
    public String reduceStock3() throws InterruptedException {
        String lockKey = "lock:product101";
        String lockValue = UUID.randomUUID().toString();
        Boolean lockValueSet = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 10, TimeUnit.SECONDS);

        if (!lockValueSet) {
            log.info("没有拿到锁");
            return "not ok";
        }
        try {
            reduce();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("开始删除锁");
            removeLock(lockKey, lockValue);
        }
        return "ok";
    }

    private void removeLock(String lockKey, String lockValue) {
        DefaultRedisScript<Void> script = new DefaultRedisScript();
        script.setResultType(Void.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("stockLock.lua")));
        redisTemplate.execute(script, Arrays.asList(lockKey),lockValue);
        String realLockValue = redisTemplate.opsForValue().get(lockKey);
        if (Objects.equals(realLockValue, lockValue)) {
            redisTemplate.delete(lockKey);
        }
    }

    @GetMapping("/reduce4")
    public String reduceStock4() throws InterruptedException {
        String lockKey = "lock:product101";
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockKey);
        readWriteLock.readLock().lock();
//        // 不续命（只存活指定时间）
//        lock.lock(10,TimeUnit.SECONDS);
//        // 不自旋
//        lock.tryLock();
//        // 续命，自旋指定时间
//        lock.tryLock(10,TimeUnit.SECONDS);
//        // 不续命（只存活指定时间），自旋指定时间
//        lock.tryLock(10,10,TimeUnit.SECONDS);
//        RLock fairLock = redissonClient.getFairLock(lockKey);
//        fairLock.lock();
        
        try {
            reduce();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("开始删除锁");
            lock.unlock();
        }
        return "ok";
    }

}
