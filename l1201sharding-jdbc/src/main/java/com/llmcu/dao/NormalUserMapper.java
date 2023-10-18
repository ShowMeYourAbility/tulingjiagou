package com.llmcu.dao;

import com.llmcu.pojo.NormalUser;
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

import static com.llmcu.dao.NormalUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface NormalUserMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<NormalUser>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    BasicColumn[] selectList = BasicColumn.columnList(userId, username, ustatus, uage);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NormalUserResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="ustatus", property="ustatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="uage", property="uage", jdbcType=JdbcType.INTEGER)
    })
    List<NormalUser> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NormalUserResult")
    Optional<NormalUser> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, normalUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, normalUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int deleteByPrimaryKey(Long userId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int insert(NormalUser row) {
        return MyBatis3Utils.insert(this::insert, row, normalUser, c ->
            c.map(userId).toProperty("userId")
            .map(username).toProperty("username")
            .map(ustatus).toProperty("ustatus")
            .map(uage).toProperty("uage")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int insertMultiple(Collection<NormalUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, normalUser, c ->
            c.map(userId).toProperty("userId")
            .map(username).toProperty("username")
            .map(ustatus).toProperty("ustatus")
            .map(uage).toProperty("uage")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int insertSelective(NormalUser row) {
        return MyBatis3Utils.insert(this::insert, row, normalUser, c ->
            c.map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(ustatus).toPropertyWhenPresent("ustatus", row::getUstatus)
            .map(uage).toPropertyWhenPresent("uage", row::getUage)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default Optional<NormalUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, normalUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default List<NormalUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, normalUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default List<NormalUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, normalUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default Optional<NormalUser> selectByPrimaryKey(Long userId_) {
        return selectOne(c ->
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, normalUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    static UpdateDSL<UpdateModel> updateAllColumns(NormalUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(row::getUserId)
                .set(username).equalTo(row::getUsername)
                .set(ustatus).equalTo(row::getUstatus)
                .set(uage).equalTo(row::getUage);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(NormalUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(row::getUserId)
                .set(username).equalToWhenPresent(row::getUsername)
                .set(ustatus).equalToWhenPresent(row::getUstatus)
                .set(uage).equalToWhenPresent(row::getUage);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int updateByPrimaryKey(NormalUser row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(ustatus).equalTo(row::getUstatus)
            .set(uage).equalTo(row::getUage)
            .where(userId, isEqualTo(row::getUserId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal_user")
    default int updateByPrimaryKeySelective(NormalUser row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(ustatus).equalToWhenPresent(row::getUstatus)
            .set(uage).equalToWhenPresent(row::getUage)
            .where(userId, isEqualTo(row::getUserId))
        );
    }
}