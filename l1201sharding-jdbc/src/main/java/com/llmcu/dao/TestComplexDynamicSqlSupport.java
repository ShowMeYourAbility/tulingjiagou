package com.llmcu.dao;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class TestComplexDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_complex")
    public static final TestComplex testComplex = new TestComplex();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_complex.cid")
    public static final SqlColumn<Long> cid = testComplex.cid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_complex.cname")
    public static final SqlColumn<String> cname = testComplex.cname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_complex.user_id")
    public static final SqlColumn<Long> userId = testComplex.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: test_complex.cstatus")
    public static final SqlColumn<String> cstatus = testComplex.cstatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: test_complex")
    public static final class TestComplex extends AliasableSqlTable<TestComplex> {
        public final SqlColumn<Long> cid = column("cid", JDBCType.BIGINT);

        public final SqlColumn<String> cname = column("cname", JDBCType.VARCHAR);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> cstatus = column("cstatus", JDBCType.VARCHAR);

        public TestComplex() {
            super("test_complex", TestComplex::new);
        }
    }
}