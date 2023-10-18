package com.llmcu.tulingjiagou.l02mysql.service.impl;

import com.llmcu.common.util.BizException;
import com.llmcu.tulingjiagou.l02mysql.dao.TestMgbDAO;
import com.llmcu.tulingjiagou.l02mysql.mapper.TestMgbMapper;
import com.llmcu.tulingjiagou.l02mysql.pojo.TestMgb;
import com.llmcu.tulingjiagou.l02mysql.service.TestMgbService;
import com.llmcu.tulingjiagou.l02mysql.service.TestMgbService2;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.llmcu.tulingjiagou.l02mysql.mapper.TestMgbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;


@Service
public class TestMgbServiceImpl implements TestMgbService {

    @Autowired
    private TestMgbMapper testMgbMapper;
    @Autowired
    private TestMgbDAO testMgbDAO;
    @Autowired
    private TestMgbService2 testMgbService2;

    @Override
    @Transactional(rollbackFor = {Exception.class},propagation = Propagation.REQUIRED)
    public void insert(TestMgb testMgb) {
        /*
        INSERT INTO test_mgb
                (
                name,
                is_chinese,
                addr
        )
        VALUES
                (
                #{name},
                #{chineseOrNot},
                #{addr}
        )
        */
        // 快速排序算法


        testMgbMapper.insertSelective(testMgb);
        try {
            getAnInt(testMgb);
        }catch (Exception e){
            e.printStackTrace();
        }
//        if (Objects.equals(testMgb.getName(), "x2")) {
//            throw new BizException("no reason");
//        }
//        testMgbService2.getAnInt(testMgb);

    }


    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void getAnInt(TestMgb testMgb) {
        testMgbMapper.insertSelective(testMgb);
        if(Objects.equals(testMgb.getName(),"xx")){
            throw new BizException("no reason");
        }
    }



    @Override
    @Transactional
    public void testUpdate(TestMgb mgb) {
        /*
        update testMgb

        */

        UpdateStatementProvider updateStatementProvider = update(testMgb)
                .set(name).equalToWhenPresent(mgb.getName())
//                .set(chineseOrNot).equalToWhenPresent(testMgb.getChineseOrNot())
                .set(addr).equalToWhenPresent(mgb.getAddr())
                .where(name, isEqualToWhenPresent(mgb.getName()))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        testMgbMapper.update(updateStatementProvider);
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void testSelect(TestMgb mgb) {
        SelectStatementProvider provider = SqlBuilder.select(testMgb.allColumns())
                .from(testMgb)
                .where(name, isLike(mgb.getName()).filter(Objects::nonNull).map(s -> "%" + s + "%"))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestMgb> testMgbs = testMgbMapper.selectMany(provider);

        SelectStatementProvider provider1 = select(id.as("A_ID"), chineseOrNot, chineseOrNot.as("is_chinese"), addr)
                .from(testMgb)
                .where(name, isLikeWhenPresent(mgb.getName()))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<TestMgb> testMgbs1 = testMgbMapper.selectMany(provider1);
        System.out.println(testMgbs);
        System.out.println(testMgbs1);
    }
}
