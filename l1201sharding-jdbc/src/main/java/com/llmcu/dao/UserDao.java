package com.llmcu.dao;

import com.llmcu.pojo.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/16 10:22
 */
@Mapper
public interface UserDao {
    List<TUser> allUsers();
    List<TUser> allUsers2();
}
