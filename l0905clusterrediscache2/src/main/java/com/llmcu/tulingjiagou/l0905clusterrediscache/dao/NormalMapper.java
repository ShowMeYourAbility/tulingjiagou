package com.llmcu.tulingjiagou.l0905clusterrediscache.dao;

import com.llmcu.tulingjiagou.l0905clusterrediscache.pojo.Normal;
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

import static com.llmcu.tulingjiagou.l0905clusterrediscache.dao.NormalDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface NormalMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Normal>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    BasicColumn[] selectList = BasicColumn.columnList(cid, cname, userId, cstatus);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NormalResult", value = {
        @Result(column="cid", property="cid", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="cstatus", property="cstatus", jdbcType=JdbcType.VARCHAR)
    })
    List<Normal> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NormalResult")
    Optional<Normal> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, normal, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, normal, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int deleteByPrimaryKey(Long cid_) {
        return delete(c -> 
            c.where(cid, isEqualTo(cid_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int insert(Normal row) {
        return MyBatis3Utils.insert(this::insert, row, normal, c ->
            c.map(cid).toProperty("cid")
            .map(cname).toProperty("cname")
            .map(userId).toProperty("userId")
            .map(cstatus).toProperty("cstatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int insertMultiple(Collection<Normal> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, normal, c ->
            c.map(cid).toProperty("cid")
            .map(cname).toProperty("cname")
            .map(userId).toProperty("userId")
            .map(cstatus).toProperty("cstatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int insertSelective(Normal row) {
        return MyBatis3Utils.insert(this::insert, row, normal, c ->
            c.map(cid).toPropertyWhenPresent("cid", row::getCid)
            .map(cname).toPropertyWhenPresent("cname", row::getCname)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(cstatus).toPropertyWhenPresent("cstatus", row::getCstatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default Optional<Normal> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, normal, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default List<Normal> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, normal, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default List<Normal> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, normal, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default Optional<Normal> selectByPrimaryKey(Long cid_) {
        return selectOne(c ->
            c.where(cid, isEqualTo(cid_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, normal, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    static UpdateDSL<UpdateModel> updateAllColumns(Normal row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cid).equalTo(row::getCid)
                .set(cname).equalTo(row::getCname)
                .set(userId).equalTo(row::getUserId)
                .set(cstatus).equalTo(row::getCstatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Normal row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(cid).equalToWhenPresent(row::getCid)
                .set(cname).equalToWhenPresent(row::getCname)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(cstatus).equalToWhenPresent(row::getCstatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int updateByPrimaryKey(Normal row) {
        return update(c ->
            c.set(cname).equalTo(row::getCname)
            .set(userId).equalTo(row::getUserId)
            .set(cstatus).equalTo(row::getCstatus)
            .where(cid, isEqualTo(row::getCid))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    default int updateByPrimaryKeySelective(Normal row) {
        return update(c ->
            c.set(cname).equalToWhenPresent(row::getCname)
            .set(userId).equalToWhenPresent(row::getUserId)
            .set(cstatus).equalToWhenPresent(row::getCstatus)
            .where(cid, isEqualTo(row::getCid))
        );
    }
}