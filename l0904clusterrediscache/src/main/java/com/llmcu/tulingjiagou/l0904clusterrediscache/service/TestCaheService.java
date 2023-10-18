package com.llmcu.tulingjiagou.l0904clusterrediscache.service;

import org.springframework.cache.annotation.Cacheable;

public interface TestCaheService {

    String sayHello(Integer id);

    @Cacheable(value="testMap",key="targetClass.getName()+'.'+methodName+'.'+#id")
    String sayHello2(Integer id);
}
