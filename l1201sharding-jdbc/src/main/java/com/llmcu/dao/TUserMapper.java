package com.llmcu.dao;

import com.llmcu.pojo.TUser;
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

import static com.llmcu.dao.TUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TUserMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<TUser>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    BasicColumn[] selectList = BasicColumn.columnList(userId, username, ustatus, uage);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TUserResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="ustatus", property="ustatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="uage", property="uage", jdbcType=JdbcType.INTEGER)
    })
    List<TUser> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TUserResult")
    Optional<TUser> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, TUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, TUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int deleteByPrimaryKey(Long userId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int insert(TUser row) {
        return MyBatis3Utils.insert(this::insert, row, TUser, c ->
            c.map(userId).toProperty("userId")
            .map(username).toProperty("username")
            .map(ustatus).toProperty("ustatus")
            .map(uage).toProperty("uage")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int insertMultiple(Collection<TUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, TUser, c ->
            c.map(userId).toProperty("userId")
            .map(username).toProperty("username")
            .map(ustatus).toProperty("ustatus")
            .map(uage).toProperty("uage")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int insertSelective(TUser row) {
        return MyBatis3Utils.insert(this::insert, row, TUser, c ->
            c.map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(ustatus).toPropertyWhenPresent("ustatus", row::getUstatus)
            .map(uage).toPropertyWhenPresent("uage", row::getUage)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default Optional<TUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, TUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default List<TUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, TUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default List<TUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, TUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default Optional<TUser> selectByPrimaryKey(Long userId_) {
        return selectOne(c ->
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, TUser, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    static UpdateDSL<UpdateModel> updateAllColumns(TUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(row::getUserId)
                .set(username).equalTo(row::getUsername)
                .set(ustatus).equalTo(row::getUstatus)
                .set(uage).equalTo(row::getUage);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(row::getUserId)
                .set(username).equalToWhenPresent(row::getUsername)
                .set(ustatus).equalToWhenPresent(row::getUstatus)
                .set(uage).equalToWhenPresent(row::getUage);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int updateByPrimaryKey(TUser row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(ustatus).equalTo(row::getUstatus)
            .set(uage).equalTo(row::getUage)
            .where(userId, isEqualTo(row::getUserId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_user")
    default int updateByPrimaryKeySelective(TUser row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(ustatus).equalToWhenPresent(row::getUstatus)
            .set(uage).equalToWhenPresent(row::getUage)
            .where(userId, isEqualTo(row::getUserId))
        );
    }
}