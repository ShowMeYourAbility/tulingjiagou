package com.llmcu.service;

import com.llmcu.dataobject.CourseUserDO;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:31
 */
public interface CourseService {
    boolean insertCourses();

    List<CourseUserDO> queryCourseWithUserInfo();
}
