package com.llmcu.service;

import com.llmcu.pojo.TDict;
import com.llmcu.pojo.TUser;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 21:38
 */
public interface UserServicce {
    boolean insertCourses();

    /**
     * 为正常表normal_user插入数据
     * @return
     */
    boolean insertNormalUser();
    List<TUser> allUsers();
    List<TUser> allUsers2();

}
