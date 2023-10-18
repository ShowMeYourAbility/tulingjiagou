package com.llmcu.dao;

import com.llmcu.pojo.TDict;
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

import static com.llmcu.dao.TDictDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TDictMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<TDict>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    BasicColumn[] selectList = BasicColumn.columnList(dictId, ustatus, uvalue);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TDictResult", value = {
        @Result(column="dict_id", property="dictId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="ustatus", property="ustatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="uvalue", property="uvalue", jdbcType=JdbcType.VARCHAR)
    })
    List<TDict> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TDictResult")
    Optional<TDict> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, TDict, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, TDict, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int deleteByPrimaryKey(Long dictId_) {
        return delete(c -> 
            c.where(dictId, isEqualTo(dictId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int insert(TDict row) {
        return MyBatis3Utils.insert(this::insert, row, TDict, c ->
            c.map(dictId).toProperty("dictId")
            .map(ustatus).toProperty("ustatus")
            .map(uvalue).toProperty("uvalue")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int insertMultiple(Collection<TDict> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, TDict, c ->
            c.map(dictId).toProperty("dictId")
            .map(ustatus).toProperty("ustatus")
            .map(uvalue).toProperty("uvalue")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int insertSelective(TDict row) {
        return MyBatis3Utils.insert(this::insert, row, TDict, c ->
            c.map(dictId).toPropertyWhenPresent("dictId", row::getDictId)
            .map(ustatus).toPropertyWhenPresent("ustatus", row::getUstatus)
            .map(uvalue).toPropertyWhenPresent("uvalue", row::getUvalue)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default Optional<TDict> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, TDict, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default List<TDict> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, TDict, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default List<TDict> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, TDict, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default Optional<TDict> selectByPrimaryKey(Long dictId_) {
        return selectOne(c ->
            c.where(dictId, isEqualTo(dictId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, TDict, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    static UpdateDSL<UpdateModel> updateAllColumns(TDict row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dictId).equalTo(row::getDictId)
                .set(ustatus).equalTo(row::getUstatus)
                .set(uvalue).equalTo(row::getUvalue);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TDict row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dictId).equalToWhenPresent(row::getDictId)
                .set(ustatus).equalToWhenPresent(row::getUstatus)
                .set(uvalue).equalToWhenPresent(row::getUvalue);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int updateByPrimaryKey(TDict row) {
        return update(c ->
            c.set(ustatus).equalTo(row::getUstatus)
            .set(uvalue).equalTo(row::getUvalue)
            .where(dictId, isEqualTo(row::getDictId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    default int updateByPrimaryKeySelective(TDict row) {
        return update(c ->
            c.set(ustatus).equalToWhenPresent(row::getUstatus)
            .set(uvalue).equalToWhenPresent(row::getUvalue)
            .where(dictId, isEqualTo(row::getDictId))
        );
    }
}