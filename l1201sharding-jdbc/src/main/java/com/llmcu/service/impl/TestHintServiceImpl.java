package com.llmcu.service.impl;

import com.llmcu.dao.TestHintMapper;
import com.llmcu.pojo.TestHint;
import com.llmcu.service.TestHintService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static com.llmcu.dao.TestHintDynamicSqlSupport.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 12:14
 */
@Service
public class TestHintServiceImpl implements TestHintService {
    private TestHintMapper testHintMapper;

    public TestHintServiceImpl(TestHintMapper testHintMapper) {
        this.testHintMapper = testHintMapper;
    }

    @Override
    public boolean insertCourses() {
        int successCnt = 0;
        Random rnd = new Random();

        int num = 20;
        for (int i = 0; i < num; i++) {
            TestHint course = new TestHint();
            course.setCname("语文");
            course.setCstatus("normal");
            course.setUserId((long) rnd.nextInt(9));
            int cnt = testHintMapper.insertSelective(course);
            successCnt += cnt;
        }

        return successCnt == num;
    }

    @Override
    public List<TestHint> queryAll() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testHint.allColumns())
                .from(testHint)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestHint> testStandards = testHintMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestHint> queryRange() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testHint.allColumns())
                .from(testHint)
                .where(userId, SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestHint> testStandards = testHintMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestHint> queryRange2() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testHint.allColumns())
                .from(testHint)
                .where(cid, SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestHint> testStandards = testHintMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestHint> queryRange3() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testHint.allColumns())
                .from(testHint)
                .where(cid, SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(cid, SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestHint> testStandards = testHintMapper.selectMany(selectStatement);
        return testStandards;
    }

    @Override
    public List<TestHint> queryRange4() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testHint.allColumns())
                .from(testHint)
                .where(userId, SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(userId, SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestHint> testStandards = testHintMapper.selectMany(selectStatement);
        return testStandards;
    }
}
