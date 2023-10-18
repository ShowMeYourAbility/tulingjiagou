package com.llmcu.service.impl;

import com.llmcu.service.NormalService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/16 17:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L01NormalServiceImplTest {

    @Autowired
    private NormalService normalService;

    @Test
    public void insertCourses() {
        boolean result = normalService.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    @Test
    public void queryAll() {
    }

    @Test
    public void queryRange() {
    }
}