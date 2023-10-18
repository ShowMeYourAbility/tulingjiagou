package com.llmcu.tulingjiagou.l0905clusterrediscache.controller;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.tulingjiagou.l0905clusterrediscache.service.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCacheController {

    @Autowired
    private TestCacheService service;

    @GetMapping("hello")
    public ResponseJson<String> sayHello(@RequestParam Integer id){
        String result = service.sayHello(id);
        return ResponseJson.success(result);
    }

    @GetMapping("hello5")
    public ResponseJson<String> sayHello5(@RequestParam Integer id){
        String result = service.sayHello5(id);
        return ResponseJson.success(result);
    }

    @GetMapping("hello1")
    public ResponseJson<String> sayHello1(@RequestParam Integer id){
        String result = service.sayHello1(id);
        return ResponseJson.success(result);
    }

    @GetMapping("hello2")
    public ResponseJson<String> sayHello2(@RequestParam Integer id){
        String result = service.sayHello2(id);
        return ResponseJson.success(result);
    }

    @GetMapping("hello3")
    public ResponseJson<String> sayHello3(@RequestParam Integer id){
        String result = service.sayHello3(id);
        return ResponseJson.success(result);
    }

    @GetMapping("hello4")
    public ResponseJson<String> sayHello4(@RequestParam Integer id){
        String result = service.sayHello4(id);
        return ResponseJson.success(result);
    }

    @GetMapping("addAndClear")
    public ResponseJson<String> addAndClear(@RequestParam Integer id){
        String result = service.addAndClear(id);
        return ResponseJson.success(result);
    }

    @GetMapping("updateAndClear")
    public ResponseJson<String> updateAndClear(@RequestParam Integer id){
        String result = service.updateAndClear(id);
        return ResponseJson.success(result);
    }

    @GetMapping("queryAndCache")
    public ResponseJson<String> queryAndCache(@RequestParam Integer id){
        String result = service.queryAndCache(id);
        return ResponseJson.success(result);
    }

    @GetMapping("addAndCache")
    public ResponseJson<String> addAndCache(@RequestParam Integer id){
        String result = service.addAndCache(id);
        return ResponseJson.success(result);
    }

    @GetMapping("updateAndCache")
    public ResponseJson<String> updateAndCache(@RequestParam Integer id){
        String result = service.updateAndCache(id);
        return ResponseJson.success(result);
    }

    @GetMapping("queryMap")
    public ResponseJson<String> queryMap(@RequestParam Integer id){
        String result = service.queryMap(id);
        return ResponseJson.success(result);
    }
}
