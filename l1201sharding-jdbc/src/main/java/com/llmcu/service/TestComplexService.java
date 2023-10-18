package com.llmcu.service;

import com.llmcu.pojo.TestComplex;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:31
 */
public interface TestComplexService {
    boolean insertCourses();

    List<TestComplex> queryAll();

    List<TestComplex> queryRange();

    List<TestComplex> queryRange2();

    List<TestComplex> queryRange3();

    List<TestComplex> queryRange4();
}
