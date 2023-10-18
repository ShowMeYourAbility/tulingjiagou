package com.llmcu.service.impl;

import com.llmcu.dataobject.CourseUserDO;
import com.llmcu.service.CourseService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2022/8/17 11:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class L02CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void insertCourses() {
        courseService.insertCourses();
    }

    @Test
    public void queryCourseWithUserInfo() {
        List<CourseUserDO> courseUserDOS = courseService.queryCourseWithUserInfo();
        log.info("result:{}",courseUserDOS);
    }
}