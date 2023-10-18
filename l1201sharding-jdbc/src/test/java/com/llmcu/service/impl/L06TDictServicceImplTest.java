package com.llmcu.service.impl;

import com.llmcu.dao.TDictMapper;
import com.llmcu.pojo.TDict;
import com.llmcu.service.TDictServicce;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 22:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L06TDictServicceImplTest {

    @Autowired
    private TDictServicce tDictServicce;

    /**
     * 测试广播表造数据
     */
    @Test
    public void insertCourses() {
        boolean result = tDictServicce.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    /**
     * 广播表的查询，非拆分表都有一份相同的完整数据，所以只从一个地方查即可
     */
    @Test
    public void queryAll() {
        List<TDict> allDictList = tDictServicce.queryAll();
        MatcherAssert.assertThat(allDictList.size(),Matchers.greaterThan(0));
    }
}