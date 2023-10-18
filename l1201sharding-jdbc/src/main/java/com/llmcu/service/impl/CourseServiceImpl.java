package com.llmcu.service.impl;

import com.llmcu.dao.CourseDao;
import com.llmcu.dao.CourseMapper;
import com.llmcu.dataobject.CourseUserDO;
import com.llmcu.pojo.Course;
import com.llmcu.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:32
 */
@Service
public class CourseServiceImpl implements CourseService {
    private CourseMapper courseMapper;
    private CourseDao courseDao;

    public CourseServiceImpl(CourseMapper courseMapper, CourseDao courseDao) {
        this.courseMapper = courseMapper;
        this.courseDao = courseDao;
    }

    @Override
    public boolean insertCourses() {
        int num = 10;
        for (int i = 0; i < num; i++) {
            Course course = new Course();
//            course.setCid((long)i);
            course.setCname("语文");
            course.setCstatus("normal");
            course.setUserId((long) i);
            courseMapper.insertSelective(course);
        }

        return true;
    }

    @Override
    public List<CourseUserDO> queryCourseWithUserInfo() {
        return courseDao.queryCourseWithUserInfo();
    }
}
