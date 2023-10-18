package com.llmcu.service.impl;

import com.llmcu.dao.NormalUserMapper;
import com.llmcu.dao.TUserMapper;
import com.llmcu.dao.UserDao;
import com.llmcu.pojo.NormalUser;
import com.llmcu.pojo.TUser;
import com.llmcu.service.UserServicce;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/16 9:44
 */
@Service
public class UserServiceImpl implements UserServicce {
    private TUserMapper tUserMapper;
    private UserDao userDao;
    private NormalUserMapper normalUserMapper;

    public UserServiceImpl(TUserMapper tUserMapper, UserDao userDao,NormalUserMapper normalUserMapper) {
        this.tUserMapper = tUserMapper;
        this.userDao = userDao;
        this.normalUserMapper = normalUserMapper;
    }

    @Override
    public boolean insertCourses() {
        int num = 10;
        int insertNum = 0;
        Random rnd = new Random();
        for (int i = 0; i < num; i++) {
            TUser user = new TUser();
            int age = rnd.nextInt(9);
            user.setUage(age);
            user.setUsername(Character.toString((char) (65 + rnd.nextInt(20))));
            user.setUstatus(age+"");
            int cnt = tUserMapper.insertSelective(user);
            insertNum += cnt;
        }
        return num == insertNum;
    }

    @Override
    public boolean insertNormalUser() {
        int num = 10;
        int insertNum = 0;
        for (int i = 0; i < num; i++) {
            NormalUser user = new NormalUser();
            user.setUserId((long)i);
            user.setUsername(i+"号用户");
            user.setUage(i+1);
            user.setUstatus(i+2+"状态");
            int cnt = normalUserMapper.insertSelective(user);

            insertNum += cnt;
        }
        return num == insertNum;
    }

    @Override
    public List<TUser> allUsers() {
        return userDao.allUsers();
    }
    @Override
    public List<TUser> allUsers2() {
        return userDao.allUsers2();
    }
}
