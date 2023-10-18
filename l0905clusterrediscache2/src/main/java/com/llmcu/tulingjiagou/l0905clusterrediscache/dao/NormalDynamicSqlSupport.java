package com.llmcu.tulingjiagou.l0905clusterrediscache.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class NormalDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    public static final Normal normal = new Normal();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cid")
    public static final SqlColumn<Long> cid = normal.cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cname")
    public static final SqlColumn<String> cname = normal.cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.user_id")
    public static final SqlColumn<Long> userId = normal.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: normal.cstatus")
    public static final SqlColumn<String> cstatus = normal.cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: normal")
    public static final class Normal extends AliasableSqlTable<Normal> {
        public final SqlColumn<Long> cid = column("cid", JDBCType.BIGINT);

        public final SqlColumn<String> cname = column("cname", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> cstatus = column("cstatus", JDBCType.VARCHAR);

        public Normal() {
            super("normal", Normal::new);
        }
    }
}