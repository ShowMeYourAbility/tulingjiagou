package com.llmcu.service;

import com.llmcu.pojo.TDict;
import com.llmcu.pojo.TestHint;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 21:38
 */
public interface TDictServicce {
    boolean insertCourses();

    List<TDict> queryAll();
}
