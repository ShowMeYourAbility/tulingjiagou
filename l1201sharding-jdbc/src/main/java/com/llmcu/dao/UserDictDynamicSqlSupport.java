package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserDictDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user_dict")
    public static final UserDict userDict = new UserDict();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user_dict.dict_id")
    public static final SqlColumn<Long> dictId = userDict.dictId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user_dict.ustatus")
    public static final SqlColumn<String> ustatus = userDict.ustatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: user_dict.uvalue")
    public static final SqlColumn<String> uvalue = userDict.uvalue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: user_dict")
    public static final class UserDict extends AliasableSqlTable<UserDict> {
        public final SqlColumn<Long> dictId = column("dict_id", JDBCType.BIGINT);

        public final SqlColumn<String> ustatus = column("ustatus", JDBCType.VARCHAR);

        public final SqlColumn<String> uvalue = column("uvalue", JDBCType.VARCHAR);

        public UserDict() {
            super("user_dict", UserDict::new);
        }
    }
}