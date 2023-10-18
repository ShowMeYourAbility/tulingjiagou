package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class TDictDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    public static final TDict TDict = new TDict();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.dict_id")
    public static final SqlColumn<Long> dictId = TDict.dictId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.ustatus")
    public static final SqlColumn<String> ustatus = TDict.ustatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: t_dict.uvalue")
    public static final SqlColumn<String> uvalue = TDict.uvalue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: t_dict")
    public static final class TDict extends AliasableSqlTable<TDict> {
        public final SqlColumn<Long> dictId = column("dict_id", JDBCType.BIGINT);

        public final SqlColumn<String> ustatus = column("ustatus", JDBCType.VARCHAR);

        public final SqlColumn<String> uvalue = column("uvalue", JDBCType.VARCHAR);

        public TDict() {
            super("t_dict", TDict::new);
        }
    }
}