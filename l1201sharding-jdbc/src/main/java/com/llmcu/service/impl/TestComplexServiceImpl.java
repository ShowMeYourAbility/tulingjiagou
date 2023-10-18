package com.llmcu.service.impl;

import com.llmcu.dao.TestComplexMapper;
import com.llmcu.pojo.TestComplex;
import com.llmcu.service.TestComplexService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static com.llmcu.dao.TestComplexDynamicSqlSupport.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 23:28
 */
@Service
public class TestComplexServiceImpl implements TestComplexService {
    private TestComplexMapper testComplexMapper;

    public TestComplexServiceImpl(TestComplexMapper testComplexMapper) {
        this.testComplexMapper = testComplexMapper;
    }

    @Override
    public boolean insertCourses() {
        int successCnt = 0;
        Random rnd = new Random();

        int num = 20;
        for (int i = 0; i < num; i++) {
            TestComplex course = new TestComplex();
            course.setCname("语文");
            course.setCstatus("normal");
            course.setUserId((long) rnd.nextInt(9));
            int cnt = testComplexMapper.insertSelective(course);
            successCnt += cnt;
        }

        return successCnt == num;
    }

    @Override
    public List<TestComplex> queryAll() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testComplex.allColumns())
                .from(testComplex)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestComplex> testComplexs = testComplexMapper.selectMany(selectStatement);
        return testComplexs;
    }

    @Override
    public List<TestComplex> queryRange() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testComplex.allColumns())
                .from(testComplex)
                .where(userId, SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestComplex> testComplexs = testComplexMapper.selectMany(selectStatement);
        return testComplexs;
    }

    @Override
    public List<TestComplex> queryRange2() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testComplex.allColumns())
                .from(testComplex)
                .where(cid, SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestComplex> testComplexs = testComplexMapper.selectMany(selectStatement);
        return testComplexs;
    }

    @Override
    public List<TestComplex> queryRange3() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testComplex.allColumns())
                .from(testComplex)
                .where(cid, SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(cid, SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestComplex> testComplexs = testComplexMapper.selectMany(selectStatement);
        return testComplexs;
    }

    @Override
    public List<TestComplex> queryRange4() {
        SelectStatementProvider selectStatement = SqlBuilder.select(testComplex.allColumns())
                .from(testComplex)
                .where(userId, SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(userId, SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestComplex> testComplexs = testComplexMapper.selectMany(selectStatement);
        return testComplexs;
    }
}
