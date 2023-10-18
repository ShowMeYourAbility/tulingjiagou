package com.llmcu.service.impl;

import com.llmcu.dao.TDictDynamicSqlSupport;
import com.llmcu.dao.TDictMapper;
import com.llmcu.pojo.TDict;
import com.llmcu.service.TDictServicce;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/15 21:39
 */
@Service
public class TDictServicceImpl implements TDictServicce {

    private TDictMapper tDictMapper;

    public TDictServicceImpl(TDictMapper tDictMapper) {
        this.tDictMapper = tDictMapper;
    }

    @Override
    public boolean insertCourses() {
        TDict dict = new TDict();
        dict.setUstatus("0");
        dict.setUvalue("不正常");
        int cnt = tDictMapper.insertSelective(dict);
        TDict dict2 = new TDict();
        dict2.setUstatus("1");
        dict2.setUvalue("正常");
        int cnt2 = tDictMapper.insertSelective(dict2);
        return cnt + cnt2 == 2;
    }

    @Override
    public List<TDict> queryAll() {
        return tDictMapper.selectMany(SqlBuilder.select(TDictDynamicSqlSupport.TDict.allColumns())
                .from(TDictDynamicSqlSupport.TDict)
                .build()
                .render(RenderingStrategies.MYBATIS3)
        );
    }
}
