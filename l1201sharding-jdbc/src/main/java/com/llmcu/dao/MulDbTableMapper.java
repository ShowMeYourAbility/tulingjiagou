package com.llmcu.dao;

import com.llmcu.pojo.MulDbTable;
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

import static com.llmcu.dao.MulDbTableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface MulDbTableMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<MulDbTable>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    BasicColumn[] selectList = BasicColumn.columnList(cid, cname, userId, cstatus);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MulDbTableResult", value = {
        @Result(column="cid", property="cid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="cstatus", property="cstatus", jdbcType=JdbcType.VARCHAR)
    })
    List<MulDbTable> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MulDbTableResult")
    Optional<MulDbTable> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, mulDbTable, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, mulDbTable, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int deleteByPrimaryKey(Long cid_) {
        return delete(c -> 
            c.where(cid, isEqualTo(cid_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int insert(MulDbTable row) {
        return MyBatis3Utils.insert(this::insert, row, mulDbTable, c ->
            c.map(cid).toProperty("cid")
            .map(cname).toProperty("cname")
            .map(userId).toProperty("userId")
            .map(cstatus).toProperty("cstatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int insertMultiple(Collection<MulDbTable> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, mulDbTable, c ->
            c.map(cid).toProperty("cid")
            .map(cname).toProperty("cname")
            .map(userId).toProperty("userId")
            .map(cstatus).toProperty("cstatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int insertSelective(MulDbTable row) {
        return MyBatis3Utils.insert(this::insert, row, mulDbTable, c ->
            c.map(cid).toPropertyWhenPresent("cid", row::getCid)
            .map(cname).toPropertyWhenPresent("cname", row::getCname)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(cstatus).toPropertyWhenPresent("cstatus", row::getCstatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default Optional<MulDbTable> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, mulDbTable, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default List<MulDbTable> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, mulDbTable, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default List<MulDbTable> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, mulDbTable, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default Optional<MulDbTable> selectByPrimaryKey(Long cid_) {
        return selectOne(c ->
            c.where(cid, isEqualTo(cid_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, mulDbTable, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    static UpdateDSL<UpdateModel> updateAllColumns(MulDbTable row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cid).equalTo(row::getCid)
                .set(cname).equalTo(row::getCname)
                .set(userId).equalTo(row::getUserId)
                .set(cstatus).equalTo(row::getCstatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MulDbTable row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cid).equalToWhenPresent(row::getCid)
                .set(cname).equalToWhenPresent(row::getCname)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(cstatus).equalToWhenPresent(row::getCstatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int updateByPrimaryKey(MulDbTable row) {
        return update(c ->
            c.set(cname).equalTo(row::getCname)
            .set(userId).equalTo(row::getUserId)
            .set(cstatus).equalTo(row::getCstatus)
            .where(cid, isEqualTo(row::getCid))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    default int updateByPrimaryKeySelective(MulDbTable row) {
        return update(c ->
            c.set(cname).equalToWhenPresent(row::getCname)
            .set(userId).equalToWhenPresent(row::getUserId)
            .set(cstatus).equalToWhenPresent(row::getCstatus)
            .where(cid, isEqualTo(row::getCid))
        );
    }
}