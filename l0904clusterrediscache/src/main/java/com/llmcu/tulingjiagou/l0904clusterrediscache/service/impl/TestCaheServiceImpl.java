package com.llmcu.tulingjiagou.l0904clusterrediscache.service.impl;

import com.llmcu.common.util.DateUtils;
import com.llmcu.tulingjiagou.l0904clusterrediscache.service.TestCaheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestCaheServiceImpl implements TestCaheService {
    @Override
    @Cacheable(value="testMap",key="targetClass.getName()+'.'+methodName+'.'+#id")
    public String sayHello(Integer id) {
        if(id>10){
            return null;
        }
        System.out.println(DateUtils.dateToString(new Date(),DateUtils.COMMON_DATE_TIME)+"没找到缓存..............");
        return "hello" + id;
    }

    @Override
    @Cacheable(value="testMap2",key="targetClass.getName()+'.'+methodName+'.'+#id")
    public String sayHello2(Integer id) {
        if(id>10){
            return null;
        }
        System.out.println(DateUtils.dateToString(new Date(),DateUtils.COMMON_DATE_TIME)+"没找到缓存..............");
        return "hello" + id;
    }

}
