package com.llmcu.dao;

import com.llmcu.pojo.TestHint;
import org.apache.ibatis.annotations.*;
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
import org.mybatis.dynamic.sql.util.mybatis3.*;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.llmcu.dao.TestHintDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TestHintMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<TestHint>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    BasicColumn[] selectList = BasicColumn.columnList(cid, cname, userId, cstatus);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TestHintResult", value = {
        @Result(column="cid", property="cid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="cstatus", property="cstatus", jdbcType=JdbcType.VARCHAR)
    })
    List<TestHint> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TestHintResult")
    Optional<TestHint> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, testHint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, testHint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int deleteByPrimaryKey(Long cid_) {
        return delete(c -> 
            c.where(cid, isEqualTo(cid_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int insert(TestHint row) {
        return MyBatis3Utils.insert(this::insert, row, testHint, c ->
            c.map(cid).toProperty("cid")
            .map(cname).toProperty("cname")
            .map(userId).toProperty("userId")
            .map(cstatus).toProperty("cstatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int insertMultiple(Collection<TestHint> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, testHint, c ->
            c.map(cid).toProperty("cid")
            .map(cname).toProperty("cname")
            .map(userId).toProperty("userId")
            .map(cstatus).toProperty("cstatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int insertSelective(TestHint row) {
        return MyBatis3Utils.insert(this::insert, row, testHint, c ->
            c.map(cid).toPropertyWhenPresent("cid", row::getCid)
            .map(cname).toPropertyWhenPresent("cname", row::getCname)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(cstatus).toPropertyWhenPresent("cstatus", row::getCstatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default Optional<TestHint> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, testHint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default List<TestHint> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, testHint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default List<TestHint> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, testHint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default Optional<TestHint> selectByPrimaryKey(Long cid_) {
        return selectOne(c ->
            c.where(cid, isEqualTo(cid_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, testHint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    static UpdateDSL<UpdateModel> updateAllColumns(TestHint row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cid).equalTo(row::getCid)
                .set(cname).equalTo(row::getCname)
                .set(userId).equalTo(row::getUserId)
                .set(cstatus).equalTo(row::getCstatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TestHint row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cid).equalToWhenPresent(row::getCid)
                .set(cname).equalToWhenPresent(row::getCname)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(cstatus).equalToWhenPresent(row::getCstatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int updateByPrimaryKey(TestHint row) {
        return update(c ->
            c.set(cname).equalTo(row::getCname)
            .set(userId).equalTo(row::getUserId)
            .set(cstatus).equalTo(row::getCstatus)
            .where(cid, isEqualTo(row::getCid))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    default int updateByPrimaryKeySelective(TestHint row) {
        return update(c ->
            c.set(cname).equalToWhenPresent(row::getCname)
            .set(userId).equalToWhenPresent(row::getUserId)
            .set(cstatus).equalToWhenPresent(row::getCstatus)
            .where(cid, isEqualTo(row::getCid))
        );
    }
}