package com.llmcu.service;

import com.llmcu.pojo.Normal;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:31
 */
public interface NormalService {
    boolean insertCourses();

    List<Normal> queryAll();

    List<Normal> queryRange();
}
