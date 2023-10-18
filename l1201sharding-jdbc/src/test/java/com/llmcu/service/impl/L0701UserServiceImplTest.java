package com.llmcu.service.impl;

import com.llmcu.pojo.TUser;
import com.llmcu.service.UserServicce;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/16 10:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class L0701UserServiceImplTest {

    @Autowired
    private UserServicce userServicce;


    /**
     * 造数据：用于测试绑定表
     */
    @Test
    public void insertCourses() {
        boolean result = userServicce.insertCourses();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    /**
     * 造数据：往正常表normal_user中插入数据，用于与拆分表course表join关联查询，以查看效果
     */
    @Test
    public void insertCourses2() {
        boolean result = userServicce.insertNormalUser();
        MatcherAssert.assertThat(result, Matchers.is(true));
    }

    /**
     * 测试绑定表
     */
    @Test
    public void allUsers() {
        List<TUser> tUsers = userServicce.allUsers();
        System.out.println(tUsers.size());
    }

    /**
     * 测试绑定表
     */
    @Test
    public void allUsers2() {
        List<TUser> tUsers = userServicce.allUsers2();
        System.out.println(tUsers.size());
    }
}