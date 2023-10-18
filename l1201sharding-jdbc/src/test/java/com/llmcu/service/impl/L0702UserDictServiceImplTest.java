package com.llmcu.service.impl;

import com.llmcu.service.UserDictServicce;
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
 * @date 2022/7/16 10:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L0702UserDictServiceImplTest {

    @Autowired
    private UserDictServicce userDictServicce;

    /**
     * 造数据用于测试绑定表
     */
    @Test
    public void insertCourses() {
        userDictServicce.insertCourses();
    }
}