package com.llmcu.service;

import com.llmcu.pojo.TestStandard;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:31
 */
public interface TestStandardService {
    boolean insertCourses();

    List<TestStandard> queryAll();

    List<TestStandard> queryRange();

    List<TestStandard> queryRange2();

    List<TestStandard> queryRange3();

    List<TestStandard> queryRange4();
}
