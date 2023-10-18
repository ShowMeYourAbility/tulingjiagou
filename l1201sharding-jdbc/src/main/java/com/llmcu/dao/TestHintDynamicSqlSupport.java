package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class TestHintDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    public static final TestHint testHint = new TestHint();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_hint.cid")
    public static final SqlColumn<Long> cid = testHint.cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_hint.cname")
    public static final SqlColumn<String> cname = testHint.cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_hint.user_id")
    public static final SqlColumn<Long> userId = testHint.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_hint.cstatus")
    public static final SqlColumn<String> cstatus = testHint.cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_hint")
    public static final class TestHint extends AliasableSqlTable<TestHint> {
        public final SqlColumn<Long> cid = column("cid", JDBCType.BIGINT);

        public final SqlColumn<String> cname = column("cname", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> cstatus = column("cstatus", JDBCType.VARCHAR);

        public TestHint() {
            super("test_hint", TestHint::new);
        }
    }
}