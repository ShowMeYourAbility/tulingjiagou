package com.llmcu.service.impl;

import com.llmcu.pojo.TestComplex;
import com.llmcu.service.TestComplexService;
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
 * @date 2022/7/14 10:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L04TestComplexServiceImplTest {

    @Autowired
    private TestComplexService testComplexService;

    @Test
    public void insertCourses() {
        boolean result = testComplexService.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    @Test
    public void queryAll() {
        List<TestComplex> result = testComplexService.queryAll();
        MatcherAssert.assertThat(result.size(),Matchers.greaterThan(0));
    }

    @Test
    public void queryRange() {
        testComplexService.queryRange();
        System.out.println(testComplexService.queryRange().size());
    }

    @Test
    public void queryRange2() {
        testComplexService.queryRange2();
    }

    @Test
    public void queryRange3() {
        testComplexService.queryRange3();
    }

    @Test
    public void queryRange4() {
        testComplexService.queryRange4();
        System.out.println(testComplexService.queryRange4().size());
    }
}