package com.llmcu.tulingjiagou.l0903singlerediscache.controller;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.tulingjiagou.l0903singlerediscache.service.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCacheController {

    @Autowired
    private TestCacheService service;

    @GetMapping("hello")
    public ResponseJson<String> hello(@RequestParam Integer id) {
        String s = service.sayHello(id);
        return ResponseJson.success(s);
    }
}
