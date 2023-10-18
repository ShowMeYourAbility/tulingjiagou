package com.llmcu.service.impl;

import com.llmcu.pojo.MulDbTable;
import com.llmcu.service.MulDbTableService;
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
 * @date 2022/7/14 10:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L02MulDbTableServiceImplTest {

    @Autowired
    private MulDbTableService mulDbTableService;

    @Test
    public void insertCourses() {
        boolean result = mulDbTableService.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    @Test
    public void queryAll() {
        List<MulDbTable> result = mulDbTableService.queryAll();
        MatcherAssert.assertThat(result.size(),Matchers.greaterThan(0));
    }

    @Test(expected = Exception.class)
    public void queryRange() {
        mulDbTableService.queryRange();
    }

    @Test(expected = Exception.class)
    public void queryRange2() {
        mulDbTableService.queryRange2();
    }

    @Test(expected = Exception.class)
    public void queryRange3() {
        mulDbTableService.queryRange3();
    }

    @Test(expected = Exception.class)
    public void queryRange4() {
        mulDbTableService.queryRange4();
    }
}