package com.llmcu.service.impl;

import com.llmcu.dao.TestStandardMapper;
import com.llmcu.pojo.TestStandard;
import com.llmcu.service.TestStandardService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static com.llmcu.dao.TestStandardDynamicSqlSupport.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 23:28
 */
@Service
public class TestStandardServiceImpl implements TestStandardService {
    private TestStandardMapper testStandardMapper;

    public TestStandardServiceImpl(TestStandardMapper testStandardMapper) {
        this.testStandardMapper = testStandardMapper;
    }

    @Override
    public boolean insertCourses() {
        int successCnt = 0;
        Random rnd = new Random();

        int num = 20;
        for (int i = 0; i < num; i++) {
            TestStandard course = new TestStandard();
            course.setCname("语文");
            course.setCstatus("normal");
            course.setUserId((long) rnd.nextInt(9));
            int cnt = testStandardMapper.insertSelective(course);
            successCnt += cnt;
        }

        return successCnt == num;
    }

    @Override
    public List<TestStandard> queryAll() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testStandard.allColumns())
                .from(testStandard)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestStandard> testStandards = testStandardMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestStandard> queryRange() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testStandard.allColumns())
                .from(testStandard)
                .where(userId, SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestStandard> testStandards = testStandardMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestStandard> queryRange2() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testStandard.allColumns())
                .from(testStandard)
                .where(cid, SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestStandard> testStandards = testStandardMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestStandard> queryRange3() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testStandard.allColumns())
                .from(testStandard)
                .where(cid, SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(cid, SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestStandard> testStandards = testStandardMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestStandard> queryRange4() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testStandard.allColumns())
                .from(testStandard)
                .where(userId, SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(userId, SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestStandard> testStandards = testStandardMapper.selectMany(selectStatement);
        return testStandards;
    }
}
