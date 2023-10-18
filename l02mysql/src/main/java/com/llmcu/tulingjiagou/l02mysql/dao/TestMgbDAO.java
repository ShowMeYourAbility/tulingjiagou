package com.llmcu.tulingjiagou.l02mysql.dao;

import com.llmcu.tulingjiagou.l02mysql.pojo.TestMgb;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMgbDAO {
    int insert(TestMgb testMgb);
}
