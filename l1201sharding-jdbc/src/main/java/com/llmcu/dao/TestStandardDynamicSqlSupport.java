package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class TestStandardDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_standard")
    public static final TestStandard testStandard = new TestStandard();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_standard.cid")
    public static final SqlColumn<Long> cid = testStandard.cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_standard.cname")
    public static final SqlColumn<String> cname = testStandard.cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_standard.user_id")
    public static final SqlColumn<Long> userId = testStandard.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_standard.cstatus")
    public static final SqlColumn<String> cstatus = testStandard.cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_standard")
    public static final class TestStandard extends AliasableSqlTable<TestStandard> {
        public final SqlColumn<Long> cid = column("cid", JDBCType.BIGINT);

        public final SqlColumn<String> cname = column("cname", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> cstatus = column("cstatus", JDBCType.VARCHAR);

        public TestStandard() {
            super("test_standard", TestStandard::new);
        }
    }
}