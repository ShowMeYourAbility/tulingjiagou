package com.llmcu.service.impl;

import com.llmcu.pojo.TestHint;
import com.llmcu.service.TestHintService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 12:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L05TestHintServiceImplTest {


    @Autowired
    private TestHintService testHintService;

    @Test
    public void insertCourses() {
        boolean result = testHintService.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    /**
     *
     */
    @Test
    public void queryAll() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.addDatabaseShardingValue("xx",1);
        hintManager.addTableShardingValue("yy",2);
        List<TestHint> result = testHintService.queryAll();
        MatcherAssert.assertThat(result.size(),Matchers.greaterThan(0));
        hintManager.close();
    }

    @Test
    public void queryRange() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.addTableShardingValue("test_hint",2);
        hintManager.addDatabaseShardingValue("test_hint",1);
        hintManager.addDatabaseShardingValue("test_hint",99);
        //setDatabaseShardingValue会覆盖addDatabaseShardingValue
//        hintManager.setDatabaseShardingValue(99);
        testHintService.queryRange();
        System.out.println(testHintService.queryRange().size());
        hintManager.close();
    }

    @Test
    public void queryRange2() {
        testHintService.queryRange2();
    }

    @Test
    public void queryRange3() {
        testHintService.queryRange3();
    }

    @Test
    public void queryRange4() {
        testHintService.queryRange4();
        System.out.println(testHintService.queryRange4().size());
    }
}