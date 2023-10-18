package com.llmcu.tulingjiagou.l02mysql.service;

import com.llmcu.tulingjiagou.l02mysql.pojo.TestMgb;
import org.springframework.transaction.annotation.Transactional;

public interface TestMgbService2 {
    void insert(TestMgb testMgb) throws Exception;

    void getAnInt(TestMgb testMgb);

    void testUpdate(TestMgb testMgb);

    void testSelect(TestMgb testMgb);
}
