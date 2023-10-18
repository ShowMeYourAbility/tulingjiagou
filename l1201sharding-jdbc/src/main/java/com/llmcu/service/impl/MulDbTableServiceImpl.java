package com.llmcu.service.impl;

import com.llmcu.dao.MulDbTableDynamicSqlSupport;
import com.llmcu.dao.MulDbTableMapper;
import com.llmcu.pojo.Course;
import com.llmcu.pojo.MulDbTable;
import com.llmcu.service.MulDbTableService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static com.llmcu.dao.MulDbTableDynamicSqlSupport.*;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 23:28
 */
@Service
public class MulDbTableServiceImpl implements MulDbTableService {
    private MulDbTableMapper mulDbTableMapper;

    public MulDbTableServiceImpl(MulDbTableMapper mulDbTableMapper) {
        this.mulDbTableMapper = mulDbTableMapper;
    }

    @Override
    public boolean insertCourses() {
        int successCnt = 0;
        Random rnd = new Random();

        int num = 20;
        for (int i = 0; i < num; i++) {
            MulDbTable course = new MulDbTable();
            course.setCname("语文");
            course.setCstatus("normal");
            course.setUserId((long) rnd.nextInt(9));
            int cnt = mulDbTableMapper.insertSelective(course);
            successCnt +=cnt;
        }

        return successCnt==num;
    }

    @Override
    public List<MulDbTable> queryAll(){
        SelectStatementProvider selectStatement = SqlBuilder.select(mulDbTable.allColumns())
                .from(mulDbTable)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<MulDbTable> mulDbTables = mulDbTableMapper.selectMany(selectStatement);
        return mulDbTables;
    }

    @Override
    public List<MulDbTable> queryRange(){
        SelectStatementProvider selectStatement = SqlBuilder.select(mulDbTable.allColumns())
                .from(mulDbTable)
                .where(userId,SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<MulDbTable> mulDbTables = mulDbTableMapper.selectMany(selectStatement);
        return mulDbTables;
    }

    @Override
    public List<MulDbTable> queryRange2(){
        SelectStatementProvider selectStatement = SqlBuilder.select(mulDbTable.allColumns())
                .from(mulDbTable)
                .where(cid,SqlBuilder.isBetweenWhenPresent(1L).and(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<MulDbTable> mulDbTables = mulDbTableMapper.selectMany(selectStatement);
        return mulDbTables;
    }

    @Override
    public List<MulDbTable> queryRange3(){
        SelectStatementProvider selectStatement = SqlBuilder.select(mulDbTable.allColumns())
                .from(mulDbTable)
                .where(cid,SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(cid,SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<MulDbTable> mulDbTables = mulDbTableMapper.selectMany(selectStatement);
        return mulDbTables;
    }

    @Override
    public List<MulDbTable> queryRange4(){
        SelectStatementProvider selectStatement = SqlBuilder.select(mulDbTable.allColumns())
                .from(mulDbTable)
                .where(userId,SqlBuilder.isGreaterThanOrEqualTo(1L))
                .and(userId,SqlBuilder.isLessThanOrEqualTo(6L))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<MulDbTable> mulDbTables = mulDbTableMapper.selectMany(selectStatement);
        return mulDbTables;
    }
}
