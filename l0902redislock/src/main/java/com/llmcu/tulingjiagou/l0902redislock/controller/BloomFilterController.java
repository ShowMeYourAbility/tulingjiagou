package com.llmcu.tulingjiagou.l0902redislock.controller;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.tulingjiagou.l0902redislock.service.BloomFilterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BloomFilterController {

    private final BloomFilterService service;

    public BloomFilterController(BloomFilterService service) {
        this.service = service;
    }

    @GetMapping("/product/query")
    public ResponseJson<String> queryProduct(Integer id){
        String result = service.queryProduct(id);
        return ResponseJson.success(result);
    }

    @GetMapping("/product/add")
    public ResponseJson<Object> addProduct(Integer id){
        service.addProduct(id);
        return ResponseJson.success();
    }
}
