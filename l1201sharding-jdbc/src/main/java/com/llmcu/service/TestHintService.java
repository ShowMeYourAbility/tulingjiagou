package com.llmcu.service;

import com.llmcu.pojo.TestHint;
import com.llmcu.pojo.TestStandard;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 12:13
 */
public interface TestHintService {
    boolean insertCourses();

    List<TestHint> queryAll();

    List<TestHint> queryRange();

    List<TestHint> queryRange2();

    List<TestHint> queryRange3();

    List<TestHint> queryRange4();
}
