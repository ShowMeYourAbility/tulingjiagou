package com.llmcu.tulingjiagou.l02mysql.controller;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.tulingjiagou.l02mysql.pojo.TestMgb;
import com.llmcu.tulingjiagou.l02mysql.service.TestMgbService;
import com.llmcu.tulingjiagou.l02mysql.service.impl.TestMgbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestMgbControlller {

    @Autowired
    private TestMgbService testMgbService;

    @Autowired
    @Qualifier("testMgbServiceImpl")
    private TestMgbServiceImpl impl;

    @PostMapping("insert")
    public ResponseJson<Object> test(@RequestBody TestMgb testMgb) throws Exception {
        impl.insert(testMgb);
        return ResponseJson.success();
    }

    @PostMapping("update")
    public ResponseJson<Object> update(@RequestBody TestMgb testMgb){
        testMgbService.testUpdate(testMgb);
        return ResponseJson.success();
    }

    @PostMapping("select")
    public ResponseJson<Object> select(@RequestBody TestMgb testMgb){
        testMgbService.testSelect(testMgb);
        return ResponseJson.success();
    }
}
