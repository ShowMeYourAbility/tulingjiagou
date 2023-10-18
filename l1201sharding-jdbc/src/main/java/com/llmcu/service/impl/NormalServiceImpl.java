package com.llmcu.service.impl;

import com.llmcu.dao.NormalMapper;
import com.llmcu.pojo.Normal;
import com.llmcu.service.NormalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/8/16 17:29
 */
@Service
public class NormalServiceImpl implements NormalService {

    private NormalMapper normalMapper;

    public NormalServiceImpl(NormalMapper normalMapper) {
        this.normalMapper = normalMapper;
    }

    @Override
    public boolean insertCourses() {
        int successCnt = 0;
        Random rnd = new Random();

        int num = 20;
        for (int i = 0; i < num; i++) {
            Normal course = new Normal();
            course.setCid((long)i);
            course.setCname("语文");
            course.setCstatus("normal");
            course.setUserId((long) rnd.nextInt(9));
            int cnt = normalMapper.insertSelective(course);
            successCnt += cnt;
        }

        return successCnt == num;
    }

    @Override
    public List<Normal> queryAll() {
        return null;
    }

    @Override
    public List<Normal> queryRange() {
        return null;
    }
}
