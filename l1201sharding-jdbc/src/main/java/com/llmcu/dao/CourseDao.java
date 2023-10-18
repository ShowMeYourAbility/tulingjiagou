package com.llmcu.dao;

import com.llmcu.dataobject.CourseUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/17 13:41
 */
@Mapper
public interface CourseDao {
    List<CourseUserDO> queryCourseWithUserInfo();
}
