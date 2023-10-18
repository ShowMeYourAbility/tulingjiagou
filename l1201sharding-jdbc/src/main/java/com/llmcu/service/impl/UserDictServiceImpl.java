package com.llmcu.service.impl;

import com.llmcu.dao.UserDictMapper;
import com.llmcu.pojo.UserDict;
import com.llmcu.service.UserDictServicce;
import org.springframework.stereotype.Service;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/16 9:53
 */
@Service
public class UserDictServiceImpl implements UserDictServicce {
    private UserDictMapper userDictMapper;

    public UserDictServiceImpl(UserDictMapper userDictMapper) {
        this.userDictMapper = userDictMapper;
    }

    @Override
    public boolean insertCourses() {
        for(int i =0;i<9;i++){
            UserDict dict = new UserDict();
            dict.setUvalue(i+"");
            dict.setUstatus(i+"岁");
            userDictMapper.insertSelective(dict);
        }
        return true;
    }
}
