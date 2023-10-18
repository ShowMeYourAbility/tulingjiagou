package com.llmcu.service.impl;

import com.llmcu.pojo.TestStandard;
import com.llmcu.service.TestStandardService;
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
public class L03TestStandardServiceImplTest {

    @Autowired
    private TestStandardService testStandardService;

    @Test
    public void insertCourses() {
        boolean result = testStandardService.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    @Test
    public void queryAll() {
        List<TestStandard> result = testStandardService.queryAll();
        MatcherAssert.assertThat(result.size(),Matchers.greaterThan(0));
    }

    @Test
    public void queryRange() {
        testStandardService.queryRange();
        System.out.println(testStandardService.queryRange().size());
    }

    @Test
    public void queryRange2() {
        testStandardService.queryRange2();
    }

    @Test
    public void queryRange3() {
        testStandardService.queryRange3();
    }

    @Test
    public void queryRange4() {
        testStandardService.queryRange4();
    }
}