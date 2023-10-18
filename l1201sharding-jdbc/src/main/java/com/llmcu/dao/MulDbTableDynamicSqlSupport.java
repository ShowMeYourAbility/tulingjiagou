package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class MulDbTableDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    public static final MulDbTable mulDbTable = new MulDbTable();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: mul_db_table.cid")
    public static final SqlColumn<Long> cid = mulDbTable.cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: mul_db_table.cname")
    public static final SqlColumn<String> cname = mulDbTable.cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: mul_db_table.user_id")
    public static final SqlColumn<Long> userId = mulDbTable.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: mul_db_table.cstatus")
    public static final SqlColumn<String> cstatus = mulDbTable.cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: mul_db_table")
    public static final class MulDbTable extends AliasableSqlTable<MulDbTable> {
        public final SqlColumn<Long> cid = column("cid", JDBCType.BIGINT);

        public final SqlColumn<String> cname = column("cname", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> cstatus = column("cstatus", JDBCType.VARCHAR);

        public MulDbTable() {
            super("mul_db_table", MulDbTable::new);
        }
    }
}