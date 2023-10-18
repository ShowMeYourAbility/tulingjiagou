package com.llmcu.tulingjiagou.l0904clusterrediscache.controller;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.common.util.DateUtils;
import com.llmcu.tulingjiagou.l0904clusterrediscache.service.TestCaheService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestCacheController {

    @Autowired
    private TestCaheService service;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("hello")
    public ResponseJson<String> sayHello(@RequestParam Integer id){
        System.out.println(DateUtils.dateToString(new Date(),DateUtils.COMMON_DATE_TIME)+"开始访问***********");
        Boolean testMapExists = redisTemplate.hasKey("testMap");
        if(!testMapExists){
            System.out.println("testMap这个key不存在%%%%%%%%%%%%%%%%%%%%%%%%");
        }
        String result = service.sayHello(id);
        return ResponseJson.success(result);
    }

    @GetMapping("hello2")
    public ResponseJson<String> sayHello2(@RequestParam Integer id){
        String result = service.sayHello2(id);
        return ResponseJson.success(result);
    }

}
