package com.llmcu.tulingjiagou.l02mysql.mapper;

import static com.llmcu.tulingjiagou.l02mysql.mapper.TestMgbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.llmcu.tulingjiagou.l02mysql.pojo.TestMgb;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface TestMgbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<TestMgb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, chineseOrNot, addr);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TestMgbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_chinese", property="chineseOrNot", jdbcType=JdbcType.TINYINT),
        @Result(column="addr", property="addr", jdbcType=JdbcType.VARCHAR)
    })
    List<TestMgb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TestMgbResult")
    Optional<TestMgb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, testMgb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, testMgb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int insert(TestMgb row) {
        return MyBatis3Utils.insert(this::insert, row, testMgb, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(chineseOrNot).toProperty("chineseOrNot")
            .map(addr).toProperty("addr")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int insertMultiple(Collection<TestMgb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, testMgb, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(chineseOrNot).toProperty("chineseOrNot")
            .map(addr).toProperty("addr")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int insertSelective(TestMgb row) {
        return MyBatis3Utils.insert(this::insert, row, testMgb, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(chineseOrNot).toPropertyWhenPresent("chineseOrNot", row::getChineseOrNot)
            .map(addr).toPropertyWhenPresent("addr", row::getAddr)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default Optional<TestMgb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, testMgb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default List<TestMgb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, testMgb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default List<TestMgb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, testMgb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default Optional<TestMgb> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, testMgb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    static UpdateDSL<UpdateModel> updateAllColumns(TestMgb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(name).equalTo(row::getName)
                .set(chineseOrNot).equalTo(row::getChineseOrNot)
                .set(addr).equalTo(row::getAddr);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TestMgb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(name).equalToWhenPresent(row::getName)
                .set(chineseOrNot).equalToWhenPresent(row::getChineseOrNot)
                .set(addr).equalToWhenPresent(row::getAddr);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int updateByPrimaryKey(TestMgb row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(chineseOrNot).equalTo(row::getChineseOrNot)
            .set(addr).equalTo(row::getAddr)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_mgb")
    default int updateByPrimaryKeySelective(TestMgb row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(chineseOrNot).equalToWhenPresent(row::getChineseOrNot)
            .set(addr).equalToWhenPresent(row::getAddr)
            .where(id, isEqualTo(row::getId))
        );
    }
}