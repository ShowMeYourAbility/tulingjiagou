package com.llmcu.service;

import com.llmcu.pojo.MulDbTable;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:31
 */
public interface MulDbTableService {
    boolean insertCourses();

    List<MulDbTable> queryAll();

    List<MulDbTable> queryRange();

    List<MulDbTable> queryRange2();

    List<MulDbTable> queryRange3();

    List<MulDbTable> queryRange4();
}
